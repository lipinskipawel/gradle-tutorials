tasks.register("run") {
    dependsOn(gradle.includedBuild("app-depends-defaults").task(":app:run"))
}
