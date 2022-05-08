plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

dependencies {
    implementation(LibraryVersions.dagger)
    kapt(LibraryVersions.daggerCompiler)
}