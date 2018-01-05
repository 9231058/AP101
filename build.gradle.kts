group = "com.github.aut-ceit.ap101"
version = "1.0-SNAPSHOT"

apply {
    plugin("java")
}

repositories {
    mavenCentral()
}

dependencies {
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
