plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinAndroidKsp)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.vuoncog.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField("String", "API_KEY", "\"564b1cb17311fa19c80abf68ed6d892a\"")
            buildConfigField("String", "BASE_URL", "\"https://api.exchangeratesapi.io/v1/\"")
        }

        release {
            buildConfigField("String", "API_KEY", "\"564b1cb17311fa19c80abf68ed6d892a\"")
            buildConfigField("String", "BASE_URL", "\"https://api.exchangeratesapi.io/v1/\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    //Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}