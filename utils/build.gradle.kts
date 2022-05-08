plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

dependencies {
    implementation(LibraryVersions.fragment)
    implementation(project(":ui-kit"))
}