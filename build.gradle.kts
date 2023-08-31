plugins {
    java
    id("se.bjurr.gitchangelog.git-changelog-gradle-plugin") version("+")
}

//tasks.generateChangelog {
//    repository = "SamuelRuwe/github-actions"
//    previousRevision = "0.0.1"
//    githubToken = System.getenv("GITHUB_TOKEN")
//}

group = "org.puregeniusness"
version = "1.0-SNAPSHOT"


// Optional config if you want to configure the changelog
tasks.register<se.bjurr.gitchangelog.plugin.gradle.GitChangelogTask>("generateGitChangelog") {
    templateContent = """
{{#tags}}
## {{name}}
 {{#issues}}
  {{#hasissue}}
   {{#haslink}}
### {{name}} [{{issue}}]({{link}}) {{title}} {{#hasissuetype}} *{{issuetype}}* {{/hasissuetype}} {{#haslabels}} {{#labels}} *{{.}}* {{/labels}} {{/haslabels}}
   {{/haslink}}
   {{^haslink}}
### {{name}} {{issue}} {{title}} {{#hasissuetype}} *{{issuetype}}* {{/hasissuetype}} {{#haslabels}} {{#labels}} *{{.}}* {{/labels}} {{/haslabels}}
   {{/haslink}}
  {{/hasissue}}
  {{^hasissue}}
### {{name}}
  {{/hasissue}}

  {{#commits}}
**{{{messagetitle}}}**

{{#messagebodyitems}}
 * {{.}}
{{/messagebodyitems}}

[{{hash}}](https://github.com/{{ownername}}/{{reponame}}/commit/{{hash}}) {{authorname}} *{{committime}}*

  {{/commits}}

 {{/issues}}
{{/tags}}
 """.trimIndent()
}

//task gitChangelogVersionTask(type: se.bjurr.gitchangelog.plugin.gradle.GitChangelogSemanticVersionTask) {
//    suffixSnapshot = true;
//    majorVersionPattern = "^[Bb]reaking"
//    minorVersionPattern = "[Ff]eature"
//    patchVersionPattern = "[Ff]ix"
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
