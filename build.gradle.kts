plugins {
    java
    id("se.bjurr.gitchangelog.git-changelog-gradle-plugin") version("+")
}

group = "org.puregeniusness"


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

tasks.register<se.bjurr.gitchangelog.plugin.gradle.GitChangelogSemanticVersionTask>("generateVersion") {
    suffixSnapshot = false
}

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

task("printVersion") {
    println("${project.version}")
}
