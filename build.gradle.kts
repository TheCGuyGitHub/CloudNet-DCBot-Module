plugins {
    kotlin("jvm") version "2.0.0"
    id("eu.cloudnetservice.juppiter") version "0.4.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "io.github.thecguygithub"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io/")
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

dependencies {

    implementation("net.dv8tion:JDA:5.0.0")

    compileOnly("eu.cloudnetservice.cloudnet:driver:4.0.0-RC10")
    compileOnly("eu.cloudnetservice.cloudnet:node:4.0.0-RC10")
    compileOnly("eu.cloudnetservice.cloudnet:bridge:4.0.0-RC10")

    compileOnly("com.github.cloudnetservice.cloud-command-framework:cloud-core:1.9.0-cn1")
    compileOnly("com.github.cloudnetservice.cloud-command-framework:cloud-annotations:1.9.0-cn1")
    implementation("net.kyori:adventure-text-minimessage:4.17.0")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
tasks {
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(21)
        options.compilerArgs.add("-Xlint:deprecation")

    }
    named("compileKotlin", org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask::class.java) {
    }
}

moduleJson {
    name = "CloudNet-DCBot-Module"
    author = "TheCGuy"
    main = "io.github.thecguygithub.cloudnet_dcbot_module.CloudNet_DCBot_Module"
    description = "This CloudNet modules allows to control the Cloud with a Discord Bot!"
}