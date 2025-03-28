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
        maven { setUrl( "https://jitpack.io" )}
        mavenCentral()
    }
}

rootProject.name = "CoinMarket"
include(":presentation")
include(":data")
include(":domain")
