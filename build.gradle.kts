plugins {
    java
    `maven-publish`
    id ("com.github.johnrengelman.shadow") version "7.1.0"
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://jitpack.io/")
}

dependencies {
    implementation("me.heroostech.citystom:CityStom:v1.0.0")
    implementation("com.moandjiezana.toml:toml4j:0.7.2")
    compileOnly("com.github.Minestom:Minestom:-SNAPSHOT")
}

group = "de.simonsator"
version = "1.5.4-RELEASE"
description = "Party-and-Friends-MySQL-Edition-Minestom-API"

publishing {
    publications.create<MavenPublication>("maven") {
        afterEvaluate {
            val shadowJar = tasks.findByName("shadowJar")
            if (shadowJar == null) from(components["java"])
            else artifact(shadowJar)
        }
    }
}

project.tasks.findByName("jar")?.enabled = false

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    mergeServiceFiles()
    archiveClassifier.set("")
}
