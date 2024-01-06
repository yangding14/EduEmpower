pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven {
            url = uri("https://my.pspdfkit.com/maven/")
        }
        jcenter()
    }
}

rootProject.name = "EduEmpowerYD"
include(":app")
 