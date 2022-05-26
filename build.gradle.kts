plugins {
    val kotlinVersion = "1.6.20-M1"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.10.0" // mirai-console version
}

mirai {
    coreVersion = "2.10.0" // mirai-core version

    publishing {
        repo = "ltcraft"
        packageName = "rcon"
        override = true
    }
}

kotlin.sourceSets.all { languageSettings.useExperimentalAnnotation("kotlin.RequiresOptIn") }

group = "cn.ltcraft.rcon"
version = "2.0.1"

repositories {
    mavenLocal()
    mavenCentral()
}