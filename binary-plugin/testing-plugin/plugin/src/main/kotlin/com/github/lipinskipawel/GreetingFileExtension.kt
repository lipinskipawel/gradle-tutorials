package com.github.lipinskipawel

import org.gradle.api.provider.Property

interface GreetingFileExtension {
    val message: Property<String>
    val greeter: Property<String>
}
