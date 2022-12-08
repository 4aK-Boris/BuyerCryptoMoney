object Dependencies {

    object Application {
        const val namespace = "aleksandr.fedotkin.buyercryptomoney"
    }

    object Android {
        const val core = "androidx.core:core-ktx:1.9.0"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"
        const val activity = "androidx.activity:activity-compose:1.6.1"
    }

    object Compose {

        private const val version = "1.3.1"
        private const val material3Version = "1.1.0-alpha02"
        const val kotlinCompilerExtensionVersion = "1.3.2"
        private const val constraintLayoutVersion = "1.0.1"
        private const val navigationVersion = "2.5.3"

        const val ui = "androidx.compose.ui:ui:$version"
        const val preview = "androidx.compose.ui:ui-tooling-preview:$version"
        const val material = "androidx.compose.material3:material3:$material3Version"
        const val navigation = "androidx.navigation:navigation-compose:$navigationVersion"

        const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:$constraintLayoutVersion"

        const val jUnit = "androidx.compose.ui:ui-test-junit4:$version"

        const val tooling = "androidx.compose.ui:ui-tooling:$version"
        const val manifest = "androidx.compose.ui:ui-test-manifest:$version"
    }

    object JUnit {
        const val jUnit = "junit:junit:4.13.2"
        const val extJUnit = "androidx.test.ext:junit:1.1.4"
    }

    object Ktor {

        private const val version = "2.1.3"

        const val client = "io.ktor:ktor-client-android:$version"
        const val negotiation = "io.ktor:ktor-client-content-negotiation:$version"
        const val json = "io.ktor:ktor-serialization-kotlinx-json:$version"
        const val logging = "io.ktor:ktor-client-logging:$version"
    }

    object Kotlin {

        const val version = "1.7.20"

        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4"
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1"
    }

    object Koin {

        private const val version = "3.3.0"

        const val koin = "io.insert-koin:koin-android:$version"
        const val compose = "io.insert-koin:koin-androidx-compose:$version"
    }

    object Coil {

        private const val version = "2.1.0"

        const val landscapist = "com.github.skydoves:landscapist-coil:$version"
    }

    object OneSignal {

        private const val version = "[4.0.0, 4.99.99]"
        const val oneSignal = "com.onesignal:OneSignal:$version"
    }

    object Plugins {

        const val gradleVersion = "7.3.1"

        const val application = "com.android.application"
        const val library = "com.android.library"
        const val kotlin = "org.jetbrains.kotlin.android"
        const val serialization = "plugin.serialization"
    }

}
