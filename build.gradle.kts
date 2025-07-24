// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://developer.huawei.com/repo/") }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.0") // or latest stable
        classpath("com.google.gms:google-services:4.4.0")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.9")
        classpath("com.huawei.agconnect:agcp:1.9.1.300")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
}

// Set Gradle version
tasks.wrapper {
    gradleVersion = "8.14.3"
}