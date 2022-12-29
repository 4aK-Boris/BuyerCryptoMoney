val koinVersion = "3.2.2"
val bouncyCastleVersion = "1.72"

plugins {
    id("org.jetbrains.kotlin.jvm")
    kotlin("plugin.serialization") version "1.7.20"
}

dependencies {

    implementation("io.insert-koin:koin-ktor:$koinVersion")
    implementation("io.insert-koin:koin-logger-slf4j:$koinVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

    implementation("org.bouncycastle:bcprov-jdk18on:$bouncyCastleVersion")
    implementation("org.bouncycastle:bcpkix-jdk18on:$bouncyCastleVersion")
    implementation("org.bouncycastle:bcutil-jdk18on:$bouncyCastleVersion")

    testImplementation("io.insert-koin:koin-test-junit4:$koinVersion")
    testImplementation("junit:junit:4.13.2")
}