package com.github.lipinskipawel

import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertNotNull

class TestingPluginTest {

    @Test
    fun `plugin registers task`() {
        // Create a test project and apply the plugin
        val project = ProjectBuilder.builder().build()
        project.plugins.apply("com.github.lipinskipawel.testing-plugin")

        // Verify the result
        assertNotNull(project.tasks.findByName("greeting"))
    }
}
