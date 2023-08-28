plugins {
    java
    id("org.shipkit.shipkit-auto-version") version "1.2.2"
    id("org.shipkit.shipkit-changelog") version "1.2.0"
}

tasks.generateChangelog {
    repository = "SamuelRuwe/github-actions"
    previousRevision = "0.0.1"
    githubToken = System.getenv("GITHUB_TOKEN")
}

group = "org.puregeniusness"
version = "1.0-SNAPSHOT"

////"org.shipkit.shipkit-changelog" {
//
//}

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

//tasks.named("generateChangelog") {
//    project.setProperty("repository", "SamuelRuwe/github-actions")
//        SamuelRuwe/github-actions
//    val previousRevision = project.ext.get("shipkit-auto-version-previous-tag")
//    println(previousRevision)
//    println("Success")
//}