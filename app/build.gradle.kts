plugins {
    id("com.android.application") // Use id("...") for plugins in app-level build file
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.b3g.cih.online"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.b3g.cih.online"
        minSdk = 26
        targetSdk = 34
        versionCode = 531
        versionName = "5.31" // Removed the duplicate "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" // Removed duplicate entry
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

    packaging { // Corrected packaging block
        jniLibs.useLegacyPackaging = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21 // Matched with the project level
        targetCompatibility = JavaVersion.VERSION_21 // Matched with the project level
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1") // Example: Use direct dependency if libs alias fails
    implementation("com.google.android.material:material:1.10.0") // Example

    // It seems you are using a version catalog (libs...).
    // Please ensure NONE of your dependencies in the libs.versions.toml
    // file have a transitive dependency on any Huawei (com.huawei.hms) library.
    // implementation(libs.appcompat)
    // implementation(libs.material)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}