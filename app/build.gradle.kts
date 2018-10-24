import com.android.build.gradle.ProguardFiles.getDefaultProguardFile
import org.gradle.internal.impldep.com.amazonaws.PredefinedClientConfigurations.defaultConfig
import org.jetbrains.kotlin.config.KotlinCompilerVersion
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "fun.triplan"
        minSdkVersion(21)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
        }
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), file("proguard-rules.pro"))
        }
    }

    dataBinding {
        isEnabled = true
    }

}


dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))

    // UI
    implementation("androidx.appcompat:appcompat:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("com.google.android.material:material:1.0.0")

    // Firebase
    implementation("com.google.firebase:firebase-core:16.0.4")
    implementation("com.google.android.gms:play-services-auth:16.0.1")
    implementation("com.google.firebase:firebase-auth:16.0.5")

    // Dagger
    implementation("com.google.dagger:dagger-android:2.16")
    implementation("com.google.dagger:dagger-android-support:2.16")
    kapt("com.google.dagger:dagger-compiler:2.16")
    kapt("com.google.dagger:dagger-android-processor:2.16")

    // AAC
    implementation("androidx.lifecycle:lifecycle-extensions:2.0.0")
    kapt("androidx.lifecycle:lifecycle-compiler:2.0.0")

    // Util
    implementation("androidx.core:core-ktx:1.0.0")


    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.1.0-beta02")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.1.0-beta02")
}


apply(mapOf("plugin" to "com.google.gms.google-services"))