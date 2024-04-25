package com.github.lipinskipawel

import org.gradle.api.Plugin
import org.gradle.api.Project

class ServerEnvironmentPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val objects = project.objects

        // create a container of data objects
        val serverEnvironments = objects.domainObjectContainer(ServerEnvironment::class.java) { name ->
            objects.newInstance(ServerEnvironment::class.java, name)
        }
        project.extensions.add("environments", serverEnvironments)

        serverEnvironments.all { serverEnvironment ->
            val env = serverEnvironment.name
            val capitalizedServerEnv = env.substring(0, 1).uppercase() + env.substring(1)
            val taskName = "deployTo$capitalizedServerEnv"
            project.tasks.register(taskName, Deploy::class.java) { task ->
                task.uri.set(serverEnvironment.url)
            }
        }
    }
}