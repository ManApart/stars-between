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
//        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    }
}
apply<KorgeGradlePlugin>()

korge {
    id = "org.rak.manapart"
    targetJvm()
    targetJs()
//
//    dependencies {
//        add("commonMainApi", "com.fasterxml.jackson.module:jackson-module-kotlin")
//    }
}