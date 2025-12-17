plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.ejercicio01t9"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.ejercicio01t9"
        minSdk = 23
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Librería para Retrofit 2
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    // Librería GSON para el tratamiento y conversión de datos JSON
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    // Librería OkHttp para simplificar la construcción de peticiones HTTP
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    // Librería para utilizar corrutinas en Kotlin (peticiones HTTP en segundo plano)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")


}