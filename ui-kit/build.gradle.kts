plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(LibraryVersions.coreKtx)
    implementation(LibraryVersions.appCompat)
    implementation(LibraryVersions.constraintlayout)
    implementation(LibraryVersions.recyclerView)
    implementation(LibraryVersions.material)
    implementation(LibraryVersions.shimmer)
    implementation(project(":core-image-loader"))
    implementation(project(":core-os"))
}