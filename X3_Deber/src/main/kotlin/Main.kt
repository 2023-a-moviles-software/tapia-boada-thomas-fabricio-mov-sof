
fun main(args: Array<String>) {

    val baseDatosSQLite = SQLite()
    baseDatosSQLite.createTable()
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}