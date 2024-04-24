import com.github.lipinskipawel.GreetingFileExtension
import com.github.lipinskipawel.GreetingPlugin
import com.github.lipinskipawel.GreetingToFileTask

plugins {
    `java-library`
    id("com.github.lipinskipawel.testing-plugin") version("0.1.0")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

val greetingFile = objects.fileProperty()

tasks.register<GreetingToFileTask>("greet") {
    destination = greetingFile
}

tasks.register("sayGreeting") {
    dependsOn("greet")
    val greetingFile = greetingFile
    doLast {
        val file = greetingFile.get().asFile
        println("${file.readText()} (file: ${file.name})")
    }
}

// setting greetingFile after task configuration
// This lazy evaluation is a key benefit of accepting any value when setting a file property and
// then resolving that value when reading the property.
greetingFile = layout.buildDirectory.file("hello.txt")

// so one plugin can have many plugin classes
apply<GreetingPlugin>()
configure<GreetingFileExtension> {
    message = "Hi"
    greeter = "Gradle"
}
