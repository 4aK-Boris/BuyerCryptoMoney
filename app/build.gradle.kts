plugins {
    id(Dependencies.Plugins.application)
    id(Dependencies.Plugins.kotlin)
    kotlin(Dependencies.Plugins.serialization) version Dependencies.Kotlin.version
}

android {
    namespace = Dependencies.Application.namespace
    compileSdk = 33

    defaultConfig {
        applicationId = Dependencies.Application.namespace
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.kotlinCompilerExtensionVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(Dependencies.Android.core)
    implementation(Dependencies.Android.lifecycle)
    implementation(Dependencies.Android.activity)

    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.preview)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.constraintLayout)
    implementation(Dependencies.Compose.navigation)

    implementation(Dependencies.Ktor.client)
    implementation(Dependencies.Ktor.negotiation)
    implementation(Dependencies.Ktor.json)
    implementation(Dependencies.Ktor.logging)

    implementation(Dependencies.Coil.landscapist)

    implementation(Dependencies.Koin.koin)
    implementation(Dependencies.Koin.compose)

    implementation(Dependencies.Kotlin.serialization)
    implementation(Dependencies.Kotlin.coroutines)

    testImplementation(Dependencies.JUnit.jUnit)

    androidTestImplementation(Dependencies.JUnit.extJUnit)

    androidTestImplementation(Dependencies.Compose.jUnit)

    debugImplementation(Dependencies.Compose.tooling)
    debugImplementation(Dependencies.Compose.manifest)
}