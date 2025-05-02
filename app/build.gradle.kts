plugins {
    alias(libs.plugins.android.application) // Android Application plugin
    alias(libs.plugins.kotlin.android) // Kotlin Android plugin
    alias(libs.plugins.kotlin.compose) // Kotlin Compose plugin
}

android {
    namespace = "com.lawal.banji.yahewa" // Application namespace
    compileSdk = 35 // SDK level for compilation

    defaultConfig {
        applicationId = "com.lawal.banji.yahewa"
        minSdk = 24 // Minimum supported Android version (Android 7.0)
        targetSdk = 35 // Target SDK level
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" // Test instrumentation
    }

    buildTypes {
        release {
            isMinifyEnabled = false // Disable code minification for release builds
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11 // Java 11 compatibility
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11" // JVM target for Kotlin compilation
    }

    buildFeatures {
        compose = true // Enable Jetpack Compose
    }
}

dependencies {
    // Jetpack Compose dependencies
    implementation(platform(libs.androidx.compose.bom)) // BOM for Compose
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material3)

    // Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.runtime.android)

    // Accompanist dependencies (required for Pager)
    implementation("com.google.accompanist:accompanist-pager:<latest-version>") // Horizontal Pager
    implementation("com.google.accompanist:accompanist-pager-indicators:<latest-version>") // Pager Indicators

    // Lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.runtime.livedata)

    // Coil for image loading in Compose
    implementation(libs.coil.compose)

    // Retrofit for networking
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    // AppCompat for backward compatibility
    implementation(libs.androidx.appcompat)

    // Room for database
    implementation(libs.androidx.room.common.jvm)

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // Debugging dependencies
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
