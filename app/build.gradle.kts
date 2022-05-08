plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    defaultConfig {
        applicationId = "com.example.cats"
        minSdk = 23
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(LibraryVersions.fragment)
    implementation(project(":core-di"))
    implementation(project(":core-di-impl"))
    implementation(project(":core-data"))
    implementation(project(":core-data-types"))
    implementation(project(":core-os"))
    implementation(project(":cats-list-impl"))
    implementation(project(":cats-favorites-api"))
    implementation(project(":cats-favorites-impl"))
}