plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies{
    implementation(LibraryVersions.coreKtx)
    implementation(LibraryVersions.appCompat)
    implementation(LibraryVersions.dagger)
    kapt(LibraryVersions.daggerCompiler)
    implementation(LibraryVersions.viewModel)
    implementation(LibraryVersions.lifecycleRuntime)
    implementation(LibraryVersions.recyclerView)
    implementation(LibraryVersions.shimmer)
    implementation(project(":ui-kit"))
    implementation(project(":core-di"))
    implementation(project(":core-di-impl"))
    implementation(project(":core-data-types"))
    implementation(project(":cats-favorites-common"))
}