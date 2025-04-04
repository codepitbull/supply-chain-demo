/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.3/userguide/building_java_projects.html in the Gradle documentation.
 */
import com.bmuschko.gradle.docker.tasks.image.Dockerfile

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    signing
    id("org.graalvm.buildtools.native").version("0.9.12")
    id("org.cyclonedx.bom").version("1.10.0")
    id("com.github.johnrengelman.shadow").version("8.1.1")
    id("com.bmuschko.docker-java-application").version("9.4.0")
    id("org.jreleaser").version("1.11.0")
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // This dependency is used by the application.
    implementation("info.picocli:picocli-shell-jline3:4.7.5")
    annotationProcessor("info.picocli:picocli-codegen:4.7.5")
    implementation("org.fusesource.jansi:jansi:2.4.1")
    implementation("com.google.dagger:dagger:2.51.1")
    annotationProcessor("com.google.dagger:dagger-compiler:2.51.1")
}

application {
    mainClass.set("de.codepitbull.cli.Cli")
}
tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    archiveClassifier.set("") // Ensures the shadow JAR is the main artifact
}

signing {
    useGpgCmd()
    sign(tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar").get())
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

graalvmNative {
    toolchainDetection.set(true)
    binaries {
        named("main") {
            javaLauncher.set(javaToolchains.launcherFor {
                languageVersion.set(JavaLanguageVersion.of(22))
                vendor.set(JvmVendorSpec.matching("Oracle Corporation"))
            })
        }
    }
}

tasks.register<Dockerfile>("createDockerfile") {
    from("eclipse-temurin:21-jre")
    copyFile("app/build/libs/app.jar", "/app/app.jar")
    entryPoint("java")
    defaultCommand("-cp", "/app/app.jar", "de.codepitbull.cli.Server")
    exposePort(8080)
}

docker {
    javaApplication {
        baseImage.set("eclipse-temurin:21-jre")
        maintainer.set("Jochen Mader")
        ports.set(listOf(8080))
        images.set(setOf("hello:latest"))
        jvmArgs.set(listOf("-Xms256m", "-Xmx2048m"))
        args.set(listOf("server"))
    }
}

tasks.cyclonedxBom {
    setIncludeConfigs(listOf("runtimeClasspath"))
    setSkipConfigs(listOf("compileClasspath", "testCompileClasspath"))
    setProjectType("application")
}


//Reproducible builds
tasks.withType<AbstractArchiveTask>().configureEach {
    isPreserveFileTimestamps = false
    isReproducibleFileOrder = true
}

tasks.named("distZip") {
    dependsOn(tasks.named("shadowJar"))
}

tasks.named("distTar") {
    dependsOn(tasks.named("shadowJar"))
}

tasks.named("startScripts") {
    dependsOn(tasks.named("shadowJar"))
}

tasks.named("startShadowScripts") {
    dependsOn(tasks.named("jar"))
}

tasks.named("signShadowJar") {
    dependsOn(tasks.named("jar"))
}

jreleaser {
    // Configure JReleaser to use the external YAML file
    configFile.set(file("jreleaser.yml"))
}
