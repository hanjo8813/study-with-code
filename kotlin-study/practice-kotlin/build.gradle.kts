dependencies {

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}