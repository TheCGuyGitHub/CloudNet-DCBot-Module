plugins {
    kotlin("jvm") version "2.0.20"
    id("eu.cloudnetservice.juppiter") version "0.4.0"
    id("io.github.goooler.shadow") version "8.1.8"
}

group = "io.github.thecguygithub"
version = "1.0-SNAPSHOT"

val jdaVersion = "5.1.0"
val kotlinCoroutinesVersion = "1.9.0-RC.2"
val cloudnetVersion = "4.0.0-RC10"
val cloudCommandFrameworkVersion = "1.9.0-cn1"

repositories {
    mavenCentral()
    maven("https://jitpack.io/")
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

dependencies {

    implementation("net.dv8tion:JDA:$jdaVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVersion")

    compileOnly("eu.cloudnetservice.cloudnet:driver:$cloudnetVersion")
    compileOnly("eu.cloudnetservice.cloudnet:node:$cloudnetVersion")
    compileOnly("eu.cloudnetservice.cloudnet:bridge:$cloudnetVersion")

    compileOnly("com.github.cloudnetservice.cloud-command-framework:cloud-core:$cloudCommandFrameworkVersion")
    compileOnly("com.github.cloudnetservice.cloud-command-framework:cloud-annotations:$cloudCommandFrameworkVersion")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(22)
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(22)
        options.compilerArgs.add("-Xlint:deprecation")
    }
    named("compileKotlin", org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask::class.java)
    shadowJar {
        dependencies {
            exclude(dependency("eu.cloudnetservice.cloudnet:.*"))
        }
    }
}

moduleJson {
    name = "CloudNet-DCBot-Module"
    author = "TheCGuy, byPixelTV"
    main = "io.github.thecguygithub.cloudnet_dcbot_module.CloudNet_DCBot_Module"
    description = "This CloudNet modules allows to control the Cloud with a Discord Bot!"
}