import com.android.build.gradle.internal.lint.AndroidLintTask
import io.gitlab.arturbosch.detekt.Detekt

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlinter)
}

android {
    namespace = "it.marcodallaba.pokedex"
    compileSdk = 34

    defaultConfig {
        applicationId = "it.marcodallaba.pokedex"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    lint {
        lintConfig = rootProject.file("build-config/lint.xml")
        //isWarningsAsErrors = true
        sarifReport = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

detekt {
    source.setFrom(files("src/main/java", "src/main/kotlin"))
    config.setFrom(rootProject.file("build-config/detekt.yml"))
    buildUponDefaultConfig = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

tasks {
    val detekt = withType<Detekt> {
        // Required for type resolution
        jvmTarget = "1.8"
        reports {
            sarif {
                required.set(true)
            }
        }
    }

    val lintReportReleaseSarifOutput = project.layout.buildDirectory.file("reports/sarif/lint-results-release.sarif")
    afterEvaluate {
        // Needs to be in afterEvaluate because it's not created yet otherwise
        named<AndroidLintTask>("lintReportRelease") {
            sarifReportOutputFile.set(lintReportReleaseSarifOutput)
        }

        val staticAnalysis by registering {
            val detektRelease by getting(Detekt::class)
            val androidLintReportRelease = named<AndroidLintTask>("lintReportRelease")

            dependsOn(detekt, detektRelease, androidLintReportRelease, lintKotlin)
        }

        register<Sync>("collectSarifReports") {
            val detektRelease by getting(Detekt::class)
            val androidLintReportRelease = named<AndroidLintTask>("lintReportRelease")

            mustRunAfter(detekt, detektRelease, androidLintReportRelease, lintKotlin, staticAnalysis)

            from(detektRelease.sarifReportFile) {
                rename { "detekt-release.sarif" }
            }
            detekt.forEach {
                from(it.sarifReportFile) {
                    rename { "detekt.sarif" }
                }
            }
            from(lintReportReleaseSarifOutput) {
                rename { "android-lint.sarif" }
            }

            into("${layout.buildDirectory}/reports/sarif")

            doLast {
                logger.info("Copied ${inputs.files.files.filter { it.exists() }} into ${outputs.files.files}")
                logger.info("Output dir contents:\n${outputs.files.files.first().listFiles()?.joinToString()}")
            }
        }
    }
}
