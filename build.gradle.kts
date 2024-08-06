import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.3.2" apply false
    id("io.spring.dependency-management") version "1.1.6"
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    `maven-publish`
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
}

group = "com.jsoncsvbridge"
version = "0.0.6"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("org.json:json:20240303")
    implementation("org.springframework.boot:spring-boot-starter-web:3.3.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencyManagement {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "21"
    }
}

tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

tasks.register<Jar>("javadocJar") {
    archiveClassifier.set("javadoc")
    from(tasks.javadoc)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "com.jsoncsvbridge"
            artifactId = "json-csv-bridge"
            version = "0.0.6"

            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])

            pom {
                name.set("json csv bridge")
                description.set("A library for converting JSON data to CSV format")
                url.set("https://github.com/hyunolike/json-csv-bridge")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                developers {
                    developer {
                        id.set("hyunolike")
                        name.set("Hyunho Jang")
                        email.set("hyunho.jang.dev@gmail.com")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/hyunolike/json-csv-bridge.git")
                    developerConnection.set("scm:git:ssh://github.com/hyunolike/json-csv-bridge.git")
                    url.set("https://github.com/hyunolike/json-csv-bridge")
                }
            }
        }
    }
}

// ktlint configuration
ktlint {
    verbose.set(true)
    outputToConsole.set(true)
    coloredOutput.set(true)
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.JSON)
    }
    filter {
        exclude("**/generated/**")
        include("**/kotlin/**")
    }

    // 와일드카드 임포트 규칙 비활성화
    disabledRules.set(setOf("no-wildcard-imports"))
}

// Optional: Add ktlint check to check task
tasks.named("check") {
    dependsOn("ktlintCheck")
}

// Optional: Add ktlint format to build task
tasks.named("build") {
    dependsOn("ktlintFormat")
}
