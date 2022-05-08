plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

dependencies {
    implementation(LibraryVersions.dagger)
    kapt(LibraryVersions.daggerCompiler)
    implementation(LibraryVersions.retrofit)
    implementation(LibraryVersions.gsonConverter)
    implementation(LibraryVersions.gson)
    implementation(LibraryVersions.okHttp)
    implementation(LibraryVersions.okHttpLogging)
    implementation(LibraryVersions.threeTenAbp)
    implementation(project(":core-di"))
    implementation(project(":core-data"))
    implementation(project(":core-os"))
}