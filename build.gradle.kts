plugins {
    application
    kotlin("jvm") version "1.9.20"
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
