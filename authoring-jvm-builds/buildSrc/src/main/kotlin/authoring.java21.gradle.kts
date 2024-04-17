plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.withType<JavaCompile> {
    doFirst {
        println("jdk version for compile: ${java.toolchain.languageVersion.get()}")
    }
}

tasks.withType<Test> {
    doFirst {
        println("jdk version for test: ${java.toolchain.languageVersion.get()}")
    }
}
