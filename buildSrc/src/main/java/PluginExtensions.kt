import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.PluginContainer
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun PluginContainer.configure(project: Project) {
    whenPluginAdded {
        project.applyKotlinCommon()
        project.applyKaptCommon()
        project.applyAndroidExtensionsCommon()

        when (this) {
            is AppPlugin -> {
                (project.extensions.getByName("android") as BaseExtension).applyAndroidCommon()
            }
            is LibraryPlugin -> {
                (project.extensions.getByName("android") as BaseExtension).applyAndroidCommon()
            }
        }
    }
}

fun Project.applyAndroidExtensionsCommon() {
    try {
        extensions.getByType(AndroidExtensionsExtension::class.java).run {
            isExperimental = true
        }
    } catch (ignore: Exception) {
    }
}

fun Project.applyKotlinCommon() {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions.jvmTarget = "11"
        kotlinOptions.freeCompilerArgs += "-Xuse-experimental=kotlin.contracts.ExperimentalContracts"
        kotlinOptions.freeCompilerArgs += "-Xuse-experimental=kotlinx.coroutines.FlowPreview"
        kotlinOptions.freeCompilerArgs += "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi"
        kotlinOptions.freeCompilerArgs += "-Xinline-classes"
    }
}

fun Project.applyKaptCommon() {
    try {
        extensions.getByType(KaptExtension::class.java).run {
            javacOptions {
                option("-Xmaxerrs", 700)
            }
        }
    } catch (ignore: Exception) {
    }
}

fun BaseExtension.applyAndroidCommon() {
    compileSdkVersion(DependencyVersions.compileSdk)
    buildToolsVersion(DependencyVersions.buildTools)

    defaultConfig {
        minSdk = DependencyVersions.minSdk
        targetSdk = DependencyVersions.targetSdk
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_11
        sourceCompatibility = JavaVersion.VERSION_11
    }

    sourceSets["main"].java.srcDir("src/main/kotlin")
}
