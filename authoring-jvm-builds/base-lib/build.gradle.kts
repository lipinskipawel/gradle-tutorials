plugins {
    `java-library`
    `java-test-fixtures`
    id("authoring.java17")
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
    testImplementation("org.assertj:assertj-core:3.20.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
