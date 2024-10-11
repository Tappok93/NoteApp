plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
}

android {
    namespace = "com.bignerdranch.android.notesapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bignerdranch.android.notesapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        dataBinding = true
        compose = true
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {

    val room_version = "2.6.1"
    val dagger_version = "2.52"

    //Dagger 2
    implementation ("com.google.dagger:dagger:$dagger_version")
    annotationProcessor ("com.google.dagger:dagger-compiler:$dagger_version")
    kapt ("com.google.dagger:dagger-compiler:$dagger_version")
    implementation ("javax.inject:javax.inject:1")


    //Push уведомления
    implementation ("androidx.core:core-ktx:1.10.1")
    implementation ("androidx.core:core:1.9.0")


    //Room database
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.10")

    //Navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation ("androidx.navigation:navigation-ui-ktx:2.6.0")
    implementation ("com.google.android.material:material:1.9.0")

    implementation ("androidx.compose.runtime:runtime:1.5.1")
    implementation("androidx.recyclerview:recyclerview:1.3.0")


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}