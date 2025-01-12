import org.gradle.declarative.dsl.schema.FqName.Empty.packageName
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)

    id("app.cash.sqldelight")
    id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            // Base implementation
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3) //Material 3 lib
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            // Koin
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.viewmodel.navigation)

            // SQLDelight
            implementation(libs.sqldelight.runtime)

            // Moko Resources
            api(libs.resources)
            api(libs.resources.compose)

            // Moko media
            api(libs.media)
            api(libs.media.compose)

            // Arrow
            implementation(libs.arrow.core)
            implementation(libs.arrow.fx.coroutines)

            // Navigator
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.transitions)

        }
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            // SQLDelight
            implementation(libs.sqldelight.android.driver)
        }

        iosMain.dependencies {
            // SQLDelight
            implementation(libs.sqldelight.native.driver)
        }
    }
}

sqldelight {
    databases {
        create("ExpenseDatabase") {
            packageName.set("com.denis.expenseapp")
        }
    }
}

android {
    namespace = "org.denis.expenseapp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.denis.expenseapp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

}

dependencies {
    debugImplementation(compose.uiTooling)
}

multiplatformResources {
    resourcesPackage.set("com.denis.expenseapp")
}