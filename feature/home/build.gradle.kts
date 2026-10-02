plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "ir.android.home"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }

}

dependencies {

    implementation(project(":ui"))
    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    testImplementation(libs.androidx.activity.ktx)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.androidx.arch.core.testing)

    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Navigation Component
    implementation(libs.androidx.navigation.compose)

    //Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)

    //Coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.kotlinx.collections.immutable)

    //Dagger Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.android.compiler)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //Room
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    //Lottie
    implementation(libs.lottie.compose)

    //Chart
    implementation(libs.mpandroidchart)
}