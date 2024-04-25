package com.github.lipinskipawel

import org.gradle.api.provider.Property
import javax.inject.Inject

abstract class ServerEnvironment @Inject constructor(
    val name: String
) {
    abstract val url: Property<String>
}
