plugins {
    id("java")
    id("org.shipkit.shipkit-auto-version") version "1.2.2"
    id("org.shipkit.shipkit-changelog") version "1.2.0"
}

group = "org.puregeniusness"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.named("generateChangelog") {
//    val previousRevision = project.ext["shipkit-auto-version-previous-tag"]
//    println(previousRevision)
    println("Success")
}