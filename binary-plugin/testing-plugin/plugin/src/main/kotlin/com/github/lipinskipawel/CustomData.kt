package com.github.lipinskipawel

import org.gradle.api.provider.Property

abstract class CustomData {
    abstract val websiteUrl: Property<String>
    abstract val vcsUrl: Property<String>


    override fun toString(): String {
        return "CustomData(websiteUrl=${websiteUrl.get()}, vcsUrl=${vcsUrl.get()})"
    }
}