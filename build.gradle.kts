plugins {
    java
    id("se.bjurr.gitchangelog.git-changelog-gradle-plugin") version("+")
}

buildscript {
    dependencies {
        classpath("se.bjurr.gitchangelog:git-changelog-lib:1.+")
    }
}

group = "org.puregeniusness"


// Optional config if you want to configure the changelog
tasks.register<se.bjurr.gitchangelog.plugin.gradle.GitChangelogTask>("generateGitChangelog") {
    templateContent = se.bjurr.gitchangelog.api.GitChangelogApi.gitChangelogApiBuilder()
        .withFromRepo(file("."))
        .withTemplatePath("changelog.mustache")
        .render()
}

tasks.register<se.bjurr.gitchangelog.plugin.gradle.GitChangelogSemanticVersionTask>("generateVersion") {
    suffixSnapshot = false
}

repositories {
    mavenCentral()
}

task("changelogVersion") {
    val result = se.bjurr.gitchangelog.api.GitChangelogApi.gitChangelogApiBuilder()
        .withFromRepo(file(".").path)
        .withSemanticMajorVersionPattern("^[Bb]reaking")
        .withSemanticMinorVersionPattern("[Ff]eature")
        .getNextSemanticVersion();
    println(result)
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

task("printVersion") {
    val (major, minor, patch) = project.version.toString().split(".")
    println(major)
}
