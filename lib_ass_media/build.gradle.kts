import com.vanniktech.maven.publish.AndroidSingleVariantLibrary

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.maven.publish)
}

android {
    namespace = "io.github.peerless2012.exo"
    // media3 1.12.0-alpha01 的 aar 元数据声明 minCompileSdk=37
    compileSdk = 37

    defaultConfig {
        // media3 1.12.0-alpha01 的 aar 声明 minSdkVersion 24，低于此值清单合并会失败
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.annotation.experimental)
    implementation(libs.androidx.media3.exo)
    implementation(libs.androidx.media3.ui)
    implementation(libs.androidx.media3.effect)
    implementation(project(":lib_ass_kt"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

mavenPublishing {
    publishToMavenCentral()
    // https://vanniktech.github.io/gradle-maven-publish-plugin/what/
    configure(AndroidSingleVariantLibrary())
    signAllPublications()
}