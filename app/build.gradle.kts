plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.eduempoweryd"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.eduempoweryd"
        minSdk = 22
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.fragment:fragment:1.6.2")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // firebase database
    implementation("com.google.firebase:firebase-database:20.3.0")

    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))

    // firebase storage
    implementation("com.google.firebase:firebase-storage:20.3.0")

    // Add the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-auth")

    // Gson library
    implementation("com.google.code.gson:gson:2.8.6")

    // Pie Chart
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    // bumptech Glide
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    implementation("com.github.bumptech.glide:glide:4.15.1")

    // Circle Image View??
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    // navigation (login)
    implementation("androidx.navigation:navigation-ui:2.7.5")
    implementation("androidx.navigation:navigation-fragment:2.7.5")

    implementation ("com.github.barteksc:android-pdf-viewer:2.0.3")
    implementation ("com.github.barteksc:android-pdf-viewer:3.2.0-beta.1")

    implementation ("com.pspdfkit:pspdfkit:8.2.1")

    implementation ("com.karumi:dexter:6.2.3")
}