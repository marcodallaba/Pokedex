plugins {
    alias(libs.plugins.kover)
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "it.marcodallaba.network"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
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
    implementation(project(":model"))

    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp.interceptor)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

val excludedFromCoverage = listOf(
    "dagger.hilt.*",
    "hilt_aggregated_deps.*",
    "*_Factory",
    "*_Factory\$*",
    "*_*Factory",
    "*_*Factory\$*",
    "*.Hilt_*",
    "*_HiltModules",
    "*_HiltModules\$*",
    "*HiltWrapper_*",
    "*_Impl",
    "*_Impl\$*",
    "*.NetworkModule",
)

koverReport {
    filters {
        excludes {
            // exclusion rules - classes to exclude from report
            classes(excludedFromCoverage)
        }
    }
}
