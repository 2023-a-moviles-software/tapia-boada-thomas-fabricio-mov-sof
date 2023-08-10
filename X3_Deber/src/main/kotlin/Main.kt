
fun main(args: Array<String>) {

    val baseDatosSQLite = SQLite()
    baseDatosSQLite.createTable()
//    baseDatosSQLite.crearTienda(3,"TiendamaniaRemake","1724443294001",983359387,"Thomas Tapia")
    println(baseDatosSQLite.consultarTiendas())
    baseDatosSQLite.eliminarTiendaPorId(1)
    println(baseDatosSQLite.consultarTiendas())
    baseDatosSQLite.actualizarTienda(2, "PepeTienda", "1712581329001",null, null)
    println(baseDatosSQLite.consultarTiendas())
    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}