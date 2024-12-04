plugins {
    kotlin("jvm") version "2.1.0"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
    exclude("**/ExampleDayTest.class")
}

tasks.register("createDayFiles") {
    doLast {
        val nextDay = fileTree("src/main/kotlin").filter { it.name.startsWith("Day") }.files.size + 1
        val dayName = "Day$nextDay"
        val dayFile = file("src/main/kotlin/$dayName.kt")
        dayFile.createNewFile()

        val exampleDayText = file("src/main/kotlin/ExampleDay.kt").readText()
        dayFile.writeText(
            exampleDayText
                .replace("ExampleDay", dayName)
                .replace("exampleDay", dayName.lowercase()),
        )

        val testFile = file("src/test/kotlin/${dayName}Test.kt")
        testFile.createNewFile()

        val exampleDayTestText = file("src/test/kotlin/ExampleDayTest.kt").readText()
        testFile.writeText(
            exampleDayTestText
                .replace("ExampleDay", dayName)
                .replace("exampleDay", dayName.lowercase()),
        )

        val resourcesFile = file("src/test/resources/${dayName.lowercase()}.txt")
        resourcesFile.createNewFile()
    }
}

kotlin {
    jvmToolchain(23)
}
