buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.3.1")
        classpath(kotlin("gradle-plugin", version = "1.3.21"))
        classpath("com.google.gms:google-services:4.2.0")
        classpath("android.arch.navigation:navigation-safe-args-gradle-plugin:1.0.0-rc01")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}