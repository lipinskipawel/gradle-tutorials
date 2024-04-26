package com.github.lipinskipawel

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.SourceSet.MAIN_SOURCE_SET_NAME
import org.gradle.api.tasks.SourceSet.TEST_SOURCE_SET_NAME
import org.gradle.api.tasks.SourceSetContainer

class GreetingPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.plugins.withType(JavaPlugin::class.java) { _ ->
            val sourceSets = project.extensions.getByType(SourceSetContainer::class.java)
            val main = sourceSets.getByName(MAIN_SOURCE_SET_NAME)
            main.java.setSrcDirs(listOf("source/main/java"))

            val test = sourceSets.getByName(TEST_SOURCE_SET_NAME)
            test.java.setSrcDirs(listOf("source/test/java"))
        }
        val extension = project.extensions.create("greeting", GreetingFileExtension::class.java)
        extension.message.convention("Convention message")
        project.task("hello").doLast {
            println("${extension.message.get()} from ${extension.greeter.get()}")
        }
    }
}