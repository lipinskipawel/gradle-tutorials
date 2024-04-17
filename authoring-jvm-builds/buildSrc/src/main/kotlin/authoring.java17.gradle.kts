plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

val test21 = task<Test>("test21") {
    javaLauncher = javaToolchains.launcherFor {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.check {
    dependsOn(test21)
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
