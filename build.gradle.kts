plugins {
    id (Dependencies.Plugins.application) version Dependencies.Plugins.gradleVersion apply false
    id (Dependencies.Plugins.library) version Dependencies.Plugins.gradleVersion apply false
    id (Dependencies.Plugins.kotlin) version Dependencies.Kotlin.version apply false
    id("org.jetbrains.kotlin.jvm") version "1.7.20" apply false
}