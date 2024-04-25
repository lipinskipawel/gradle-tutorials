package com.github.lipinskipawel

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class Deploy : DefaultTask() {
    @get:Input
    abstract val uri: Property<String>

    @TaskAction
    fun taskAction() {
        println("hello, and the uri is ${uri.get()}")
    }
}
