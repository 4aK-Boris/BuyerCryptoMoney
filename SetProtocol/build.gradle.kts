plugins {
    id("org.jetbrains.kotlin.jvm")
    kotlin(Dependencies.Plugins.serialization) version Dependencies.Kotlin.version
}

dependencies {

    implementation(Dependencies.Koin.koinCore)
    implementation(Dependencies.Koin.koinLogger)

    implementation(Dependencies.Kotlin.serialization)
    implementation(Dependencies.Kotlin.coroutines)
    implementation(Dependencies.Kotlin.datetime)

    implementation(Dependencies.Ktor.client)
    implementation(Dependencies.Ktor.negotiation)
    implementation(Dependencies.Ktor.json)
    implementation(Dependencies.Ktor.logging)
    implementation(Dependencies.Ktor.websockets)
    implementation(project(":Network"))

    testImplementation(Dependencies.Koin.koinJUnit)

    testImplementation(Dependencies.JUnit.jUnit)
}