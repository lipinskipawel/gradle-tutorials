plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// configure task
tasks.compileJava {
    options.isFork = false
}

// configure all tasks with type
tasks.withType<JavaCompile> {
    options.isFork = false
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
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
}
