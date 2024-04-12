rootProject.name = "composite-build"

includeBuild("app-depends-defaults")

file("modules")
    .listFiles()
    .forEach { moduleBuild: File ->
        includeBuild(moduleBuild)
    }
