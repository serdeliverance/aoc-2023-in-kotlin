plugins {
    application
    kotlin("jvm") version "1.9.20"
    id("com.diffplug.spotless") version "6.23.2"
}


tasks {
    wrapper {
        gradleVersion = "8.5"
    }
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    testImplementation(kotlin("test"))
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    kotlin {
        target("**/*.kt")
        ktlint()
    }
}
