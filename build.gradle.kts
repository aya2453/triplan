buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.2.0-rc02")
        classpath(kotlin("gradle-plugin", version = "1.2.60"))
        classpath("com.google.gms:google-services:4.0.1")
    }
}

allprojects {
    repositories {
        jcenter()
        google()
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}