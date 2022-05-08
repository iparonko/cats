plugins {
    id("com.android.library")
    kotlin("android")
}

dependencies {
    implementation(project(":cats-favorites-impl"))
    implementation(LibraryVersions.fragment)
}