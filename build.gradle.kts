plugins {
    kotlin("jvm") version "1.9.22"
    application
    kotlin("plugin.serialization") version "1.9.22"
}

group = "net.grossmax.journal"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("io.ktor:ktor-server-core:2.3.9")
    implementation("io.ktor:ktor-server-netty:2.3.9")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.9")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.9")


    implementation("ch.qos.logback:logback-classic:1.5.3")

    implementation("com.sksamuel.hoplite:hoplite-core:2.7.5")
    implementation("com.sksamuel.hoplite:hoplite-yaml:2.7.5")

    implementation(platform("io.insert-koin:koin-bom:3.5.0"))
    implementation("io.insert-koin:koin-core")

    implementation("org.ktorm:ktorm-core:3.6.0")
    implementation("org.ktorm:ktorm-support-mysql:3.6.0")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.3.3")
    implementation("com.zaxxer:HikariCP:5.1.0")

    // datetime
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")




}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

application {
    mainClass = "net.grossmax.journal.ktor.MainKt"


}

tasks.named<JavaExec>("run") {
    workingDir = File("run")
}