buildscript {
    repositories {
        google()
        jcenter()
        maven(url = "https://maven.google.com")
        maven(url = "https://developer.huawei.com/repo/")
    }

    dependencies {
        classpath(GradlePluginVersions.android)
        classpath(GradlePluginVersions.kotlin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    }
}
allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven(url = "https://maven.google.com")
        maven(url = "https://clojars.org/repo")
        maven(url = "https://developer.huawei.com/repo/")
        maven(url = "https://maven.sumsub.com/repository/maven-public/")
    }
}

subprojects {
    plugins.configure(this)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}