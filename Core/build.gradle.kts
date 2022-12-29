plugins {
    id(Dependencies.Plugins.library)
    id(Dependencies.Plugins.kotlin)
}

android {
    namespace = Dependencies.Application.coreModuleNameSpace
    compileSdk = 33

    defaultConfig {
        minSdk = 28
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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

    implementation(Dependencies.Koin.koinCore)
    implementation(Dependencies.Koin.compose)
    implementation(Dependencies.Koin.koinLogger)
    implementation(Dependencies.Koin.koinAndroid)

    implementation(Dependencies.OneSignal.oneSignal)

    testImplementation(Dependencies.Koin.koinJUnit)

    testImplementation(Dependencies.JUnit.jUnit)

    androidTestImplementation(Dependencies.JUnit.extJUnit)

    androidTestImplementation(Dependencies.Compose.jUnit)

    debugImplementation(Dependencies.Compose.tooling)
    debugImplementation(Dependencies.Compose.manifest)
}