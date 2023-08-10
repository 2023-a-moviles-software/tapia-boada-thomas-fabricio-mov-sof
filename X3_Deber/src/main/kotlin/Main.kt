
fun main(args: Array<String>) {

//    val baseDatosSQLite = SQLite()
//    baseDatosSQLite.createTable()
////    baseDatosSQLite.crearTienda(3,"TiendamaniaRemake","1724443294001",983359387,"Thomas Tapia")
//    println(baseDatosSQLite.consultarTiendas())
//    baseDatosSQLite.eliminarTiendaPorId(1)
//    println(baseDatosSQLite.consultarTiendas())
//    baseDatosSQLite.actualizarTienda(2, "PepeTienda", "1712581329001",null, null)
//    println(baseDatosSQLite.consultarTiendasPorId(2))

    val menu : Menu = Menu()
    var seleccion : Int = 0

    while(seleccion != 6) {
        menu.mostrarMenuTiendas()
        println("Seleccione una opcion: ")
        seleccion = readln().toInt()
        when (seleccion){
            1 -> {
                menu.mostrarTiendas()
            }
            2 -> {
                menu.mostrarTiendas()
                println("Ingrese el ID de la tienda: ")
                var id = readln().toInt()
                menu.seleccionarTienda(id)
            }
            3 -> {
                println("Ingrese el ID de la Tienda: ")
                var id = readln().toInt()
                println("Ingrese el NOMBRE DE LA TIENDA: ")
                var nombreTienda = readln()
                println("Ingrese el RUC asociado a la tienda: ")
                var ruc = readln()
                println("Ingrese el TELEFONO de la tienda: ")
                var telefono = readln().toInt()
                println("Ingrese el NOMBRE DEL PROPIETARIO de la tienda: ")
                var nombrePropietario = readln()
                menu.agregarTienda(id, nombreTienda, ruc, telefono, nombrePropietario)
            }
            4-> {
                menu.mostrarTiendas()
                println("Ingrese el ID de la tienda a ELIMINAR: ")
                var id = readln().toInt()
                menu.eliminarTienda(id)
            }
            5 -> {
                menu.mostrarTiendas()
                println("Ingrese el ID de la tienda a MODIFICAR: ")
                var id = readln().toInt()
                var tienda = menu.seleccionarTienda(id)
                println("\n")
                println("**EN CASO DE NO ALTERAR UN CAMPO DEJE EN BLANCO**")
                println("Ingrese el NOMBRE DE LA TIENDA: ")
                var nombreTienda : String? = readln()
                if (nombreTienda == ""){
                    nombreTienda = tienda?.nombreTienda
                }
                println("Ingrese el RUC asociado a la tienda: ")
                var ruc : String? = readln()
                if (ruc == ""){
                    ruc = tienda?.ruc
                }
                println("Ingrese el TELEFONO de la tienda: ")
                var telefono : String? = readln()
                var telefonoInt : Int?
                if (telefono == ""){
                    telefonoInt = tienda?.telefono
                } else {
                    telefonoInt = telefono?.toInt()
                }
                println("Ingrese el NOMBRE DEL PROPIETARIO de la tienda: ")
                var nombrePropietario : String? = readln()
                if (nombrePropietario == ""){
                    nombrePropietario = tienda?.nombrePropietario
                }
                menu.modificarTienda(id, nombreTienda, ruc, telefonoInt, nombrePropietario)

            }
        }
    }
}