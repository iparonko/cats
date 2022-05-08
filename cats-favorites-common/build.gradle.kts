plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

dependencies {
    implementation(LibraryVersions.dagger)
    kapt(LibraryVersions.daggerCompiler)
    implementation(LibraryVersions.liveData)
    implementation(project(":core-di"))
    implementation(project(":core-di-impl"))
    implementation(project(":core-data"))
}