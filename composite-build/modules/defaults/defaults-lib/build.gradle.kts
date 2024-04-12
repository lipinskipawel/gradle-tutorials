plugins {
    `java-library`
}

repositories {
    mavenCentral()
    flatDir {
        dirs("libs")
    }
}

group = "org.example.defaults"
version = "1.0"

dependencies {
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation(":assertj-core-3.20.2")
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
