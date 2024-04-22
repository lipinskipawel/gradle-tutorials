package com.github.lipinskipawel

import org.gradle.api.Plugin
import org.gradle.api.Project

class TestingPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        // Register a task
        project.tasks.register("greeting") { task ->
            task.doLast {
                println("Hello from plugin 'com.github.lipinskipawel.testing-plugin'")
            }
        }
    }
}
