plugins {
    `java-library`
    `java-test-fixtures`
    id("authoring.java17")
}

dependencies {
    testImplementation(enforcedPlatform(project(":standard")))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.25.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
