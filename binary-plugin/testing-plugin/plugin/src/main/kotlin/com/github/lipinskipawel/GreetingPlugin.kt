package com.github.lipinskipawel

import org.gradle.api.Plugin
import org.gradle.api.Project

class GreetingPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val extension = project.extensions.create("greeting", GreetingFileExtension::class.java)
        extension.message.convention("Convention message")
        project.task("hello").doLast {
            println("${extension.message.get()} from ${extension.greeter.get()}")
        }
    }
}