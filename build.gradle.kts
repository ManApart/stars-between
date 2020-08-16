plugins {
    java
    kotlin("jvm") version "1.3.72"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.java-websocket:Java-WebSocket:1.4.0")
    implementation(group = "com.fasterxml.jackson.module", name = "jackson-module-kotlin", version = "2.9.0")
    implementation("org.slf4j:slf4j-simple:1.7.25")
    testImplementation("junit", "junit", "4.12")
}
