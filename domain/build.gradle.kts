plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_18
    targetCompatibility = JavaVersion.VERSION_18
}

dependencies {

    //Coroutines
    implementation(libs.kotlinx.coroutines.android)

    //paging
    implementation(libs.androidx.paging.common)

}