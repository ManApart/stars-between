import com.soywiz.korge.gradle.*

group = "org.rak.manapart"
version = ""

buildscript {
    val korgePluginVersion: String by project

    repositories {
        mavenLocal()
        mavenCentral()
        google()
        maven { url = uri("https://plugins.gradle.org/m2/") }
    }
    dependencies {
        classpath("com.soywiz.korlibs.korge.plugins:korge-gradle-plugin:$korgePluginVersion")
    }
}
apply<KorgeGradlePlugin>()


plugins {
    id("com.soywiz.korge")
}

korge {
    id = "org.rak.manapart"
    targetJvm()
    targetJs()
}


kotlin {
    sourceSets {
        val commonMain by getting {
            kotlin.srcDir("src/commonMain/kotlin")
            dependencies {
                implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.4")
            }
        }
    }
}