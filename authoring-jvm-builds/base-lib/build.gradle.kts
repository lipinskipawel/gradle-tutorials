plugins {
    `java-library`
    `java-test-fixtures`
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}
