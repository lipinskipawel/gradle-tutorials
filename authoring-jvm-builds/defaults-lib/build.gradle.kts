plugins {
    `java-library`
    // the idea plugin is just from intellij to understand the testIntegration directory
    // gradle is working correctly without it
    idea
}

repositories {
    mavenCentral()
    flatDir {
        dirs("libs")
    }
}

group = "org.example.defaults"
version = "1.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
    // changing the junit XML file location
    testResultsDir = layout.buildDirectory.dir("junit-custom") // instead default test-results
}

// more about configuring source sets
// https://docs.gradle.org/current/dsl/org.gradle.api.tasks.SourceSet.html
// https://docs.gradle.org/current/dsl/org.gradle.api.file.SourceDirectorySet.html
sourceSets {
    main {
        java {
            // override default src/main/java
            setSrcDirs(listOf("source/java"))
            // add dir with src
            srcDirs("otherSrc/src/main/java")
        }
    }

    test {
        java {
            setSrcDirs(listOf("test/java"))
        }
    }

    create("testIntegration") {
        // this is also for intellij
        java {
            setSrcDirs(listOf("testIntegration/java"))
        }
        // this is for gradle
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output
    }
}

idea {
    module {
        testSources.from(sourceSets["testIntegration"].java.srcDirs)
    }
}

// more about what configurations has been created
// https://docs.gradle.org/current/userguide/java_plugin.html#java_source_set_configurations
val testIntegrationImplementation: Configuration by configurations.getting {
    // this means that all the declared dependencies of production code become dependencies of the testIntegration
    extendsFrom(configurations.implementation.get())
    // extendsFrom(configurations.testImplementation.get())
}

// this means that all the declared dependencies of production code become dependencies of the testIntegration
val testIntegrationRuntimeOnly: Configuration by configurations.getting
configurations["testIntegrationRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())

val testIntegration = task<Test>("testIntegration") {
    description = "Runs integration tests."
    group = "verification"

    failFast = false
    ignoreFailures = true
    shouldRunAfter("test")

    useJUnitPlatform {
        includeEngines("junit-jupiter")
    }

    testLogging {
        events("passed", "failed", "skipped")
    }
}

tasks.check { dependsOn(testIntegration) }

dependencies {
    // https://docs.gradle.org/current/userguide/dependency_constraints.html#dependency-constraints
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation(":assertj-core-3.20.2")
    testImplementation(testFixtures(project(":base-lib")))

    testIntegrationImplementation(libs.junit.jupiter)
    testIntegrationRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testIntegrationImplementation(":assertj-core-3.20.2")
}

// configure task
tasks.compileJava {
    options.isFork = false
}

// configure all tasks with type
tasks.withType<JavaCompile> {
    options.isFork = false
}

// https://docs.gradle.org/current/dsl/org.gradle.api.tasks.testing.Test.html
// lazy configuration
// https://docs.gradle.org/current/userguide/task_configuration_avoidance.html#sec:task_configuration_avoidance_pitfalls
tasks.named<Test>("test") {
    useJUnitPlatform {
        includeEngines("junit-jupiter")
    }

    maxHeapSize = "1G"
    ignoreFailures = true
    failFast = false
    testLogging {
        events("failed", "skipped") // check TestLogEvent for options
    }
    maxParallelForks = 1
    // ./gradlew test --tests "org.example.LibraryTest.should_skip" --continuous
    // configures the junit xml
    reports {
        junitXml.apply {
            isOutputPerTestCase = true
            mergeReruns = true
        }
    }
    isScanForTestClasses = false
}
