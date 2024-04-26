package com.github.lipinskipawel

import org.gradle.api.Action
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Nested
import javax.inject.Inject

abstract class ServerEnvironment @Inject constructor(
    val name: String
) {
    abstract val url: Property<String>

    @get:Nested
    abstract val customData: CustomData

    fun customData(action: Action<in CustomData>) {
        action.execute(customData)
    }
}
