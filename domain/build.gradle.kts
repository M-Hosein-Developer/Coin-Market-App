plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_18
    targetCompatibility = JavaVersion.VERSION_18
}

dependencies {

    //Coroutines
    implementation(libs.kotlinx.coroutines.android)

    //paging
    testImplementation(libs.androidx.paging.common)
    implementation(libs.androidx.paging.common.jvm)


}