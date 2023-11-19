plugins {
    kotlin("jvm") version "1.9.20"
    application
}

group = "fr.ionas"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("io.strikt:strikt-core:0.34.1")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}


sourceSets {
    test {
        kotlin.srcDir("src/test/kotlin")
    }
}