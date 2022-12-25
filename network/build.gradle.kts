plugins {
    id("org.jetbrains.kotlin.jvm")
}

dependencies {
    implementation(Dependencies.Ktor.client)
    implementation(Dependencies.Ktor.negotiation)
    implementation(Dependencies.Ktor.json)
    implementation(Dependencies.Ktor.logging)
    implementation(Dependencies.Ktor.websockets)

    implementation(Dependencies.Koin.koinCore)
}
