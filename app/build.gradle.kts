import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("org.sonarqube") version "2.7"
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

    lintOptions {
        isCheckReleaseBuilds = false
        isAbortOnError = false
    }

    dataBinding {
        isEnabled = true
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))

    // UI
    implementation("androidx.appcompat:appcompat:1.1.0-alpha03")
    implementation ("androidx.activity:activity:1.0.0-alpha05")
    implementation("androidx.constraintlayout:constraintlayout:2.0.0-alpha3")
    implementation("com.google.android.material:material:1.0.0")
    implementation("android.arch.navigation:navigation-fragment:1.0.0")
    implementation("android.arch.navigation:navigation-ui:1.0.0")

    // Firebase
    implementation("com.google.firebase:firebase-core:16.0.8")
    implementation("com.google.android.gms:play-services-auth:16.0.1")
    implementation("com.google.firebase:firebase-auth:16.2.0")

    // Dagger
    implementation("com.google.dagger:dagger-android:2.21")
    implementation("com.google.dagger:dagger-android-support:2.21")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    kapt("com.google.dagger:dagger-compiler:2.21")
    kapt("com.google.dagger:dagger-android-processor:2.21")


    // AAC
    implementation("androidx.lifecycle:lifecycle-extensions:2.0.0")
    kapt("androidx.lifecycle:lifecycle-compiler:2.0.0")

    // Util
    implementation("androidx.core:core-ktx:1.0.1")
    implementation("com.jakewharton.timber:timber:4.7.1")

    implementation("com.squareup.retrofit2:retrofit:2.5.0")
    implementation("com.squareup.moshi:moshi:1.8.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.5.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.8.0")


    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.1.1")
}


apply(mapOf("plugin" to "com.google.gms.google-services"))
