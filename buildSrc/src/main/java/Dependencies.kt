object DependencyVersions {
    const val buildTools = "30.0.3"
    const val compileSdk = 31
    const val minSdk = 23
    const val targetSdk = 31

    const val retrofit = "2.9.0"
    const val kotlin = "1.5.31"
    const val dagger = "2.40.5"
    const val okHttp = "4.9.3"
    const val lifecycle = "2.4.0"
    const val constraintLayout = "2.1.2"
    const val coroutines = "1.6.0"
    const val room = "2.4.2"
    const val navigation = "2.4.2"
}

object GradlePluginVersions {
    const val android = "com.android.tools.build:gradle:7.0.4"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${DependencyVersions.kotlin}"
}

object LibraryVersions {
    const val retrofit = "com.squareup.retrofit2:retrofit:${DependencyVersions.retrofit}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${DependencyVersions.retrofit}"

    const val okHttp = "com.squareup.okhttp3:okhttp:${DependencyVersions.okHttp}"
    const val okHttpLogging =
        "com.squareup.okhttp3:logging-interceptor:${DependencyVersions.okHttp}"
    const val gson = "com.google.code.gson:gson:2.8.9"

    const val dagger = "com.google.dagger:dagger:${DependencyVersions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${DependencyVersions.dagger}"

    const val viewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${DependencyVersions.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${DependencyVersions.lifecycle}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${DependencyVersions.lifecycle}"

    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${DependencyVersions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${DependencyVersions.coroutines}"

    const val coreKtx = "androidx.core:core-ktx:1.7.0"
    const val appCompat = "androidx.appcompat:appcompat:1.4.1"
    const val recyclerView = "androidx.recyclerview:recyclerview:1.2.1"
    const val fragment = "androidx.fragment:fragment-ktx:1.4.1"
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${DependencyVersions.constraintLayout}"
    const val shimmer = "io.supercharge:shimmerlayout:2.1.0"
    const val picasso = "com.squareup.picasso:picasso:2.71828"
    const val material = "com.google.android.material:material:1.5.0"

    const val threeTenAbp = "com.jakewharton.threetenabp:threetenabp:1.3.0"

    const val roomRuntime = "androidx.room:room-runtime:${DependencyVersions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${DependencyVersions.room}"
    const val roomKtx = "androidx.room:room-ktx:${DependencyVersions.room}"
}