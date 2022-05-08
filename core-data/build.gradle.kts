plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    defaultConfig {
        buildConfigField("String", "X_API_KEY", "\"DEMO-API-KEY\"")
    }
}

dependencies {
    implementation(LibraryVersions.dagger)
    kapt(LibraryVersions.daggerCompiler)
    implementation(LibraryVersions.retrofit)
    implementation(LibraryVersions.gson)
    implementation(LibraryVersions.okHttp)
    implementation(LibraryVersions.threeTenAbp)
    implementation(LibraryVersions.coroutinesCore)
    implementation(LibraryVersions.coroutinesAndroid)
    implementation(LibraryVersions.liveData)
    api(LibraryVersions.roomRuntime)
    kapt(LibraryVersions.roomCompiler)
    implementation(LibraryVersions.roomKtx)
    implementation(project(":core-data-types"))
    implementation(project(":core-os"))
    implementation(project(":core-di"))
    implementation(project(":utils"))
}