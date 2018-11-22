buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.2.1")
        classpath(kotlin("gradle-plugin", version = "1.3.0"))
        classpath("com.google.gms:google-services:4.2.0")
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