plugins {
    embeddedKotlin("jvm")
    embeddedKotlin("plugin.serialization")
    alias(libs.plugins.ktlint)
    application
}

repositories {
    mavenCentral()
}

dependencies {
    /*
     * Since Kotlin 1.4, the standard library has been added automatically.
     * https://kotlinlang.org/docs/reference/whatsnew14.html#dependency-on-the-standard-library-added-by-default
     */

    /*
     * Since Kotlin 1.1.2, the versions of artifacts of the group "org.jetbrains.kotlin" have been optional and inferred from the applied plugin.
     * (No documentation about it is found.)
     */

    implementation(libs.coroutines.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.moshi.kotlin)
    implementation(libs.rxjava)
    testImplementation(kotlin("test"))
    testImplementation(libs.coroutines.test)
    testImplementation(libs.mockito)
    testImplementation(libs.turbine)
}

tasks.test {
    useJUnitPlatform()
}
