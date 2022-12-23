plugins {
    id(Dependencies.Plugins.library)
    id(Dependencies.Plugins.kotlin)
    kotlin(Dependencies.Plugins.serialization) version Dependencies.Kotlin.version
}

android {
    namespace = "aleksandr.fedotkin.set.protocol"
    compileSdk = 33

    defaultConfig {
        minSdk = 28
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    implementation(Dependencies.Koin.koinCore)
    implementation(Dependencies.Koin.compose)
    implementation(Dependencies.Koin.koinLogger)
    implementation(Dependencies.Koin.koinAndroid)

    implementation(Dependencies.Kotlin.serialization)
    implementation(Dependencies.Kotlin.coroutines)
    implementation(Dependencies.Kotlin.datetime)

    implementation(Dependencies.Ktor.client)
    implementation(Dependencies.Ktor.negotiation)
    implementation(Dependencies.Ktor.json)
    implementation(Dependencies.Ktor.logging)
    implementation(Dependencies.Ktor.websockets)
    
    testImplementation(Dependencies.Koin.koinJUnit)

    testImplementation(Dependencies.JUnit.jUnit)

    androidTestImplementation(Dependencies.JUnit.extJUnit)
}