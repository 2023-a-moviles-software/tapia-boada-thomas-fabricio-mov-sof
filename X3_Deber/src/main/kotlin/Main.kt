
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
    var opcion : Int = 0

    while(seleccion != 6) {
        menu.mostrarMenuTiendas()
        println("Seleccione una opcion: ")
        seleccion = readln().toInt()
        when (seleccion){
            1 -> { //Mostrar Tiendas
                menu.mostrarTiendas()
            }
            2 -> { //Seleccionar Tienda
                menu.mostrarTiendas()
                println("Ingrese el ID de la tienda: ")
                var id = readln().toInt()
                menu.seleccionarTienda(id)
                while (opcion != 5){  //Menu de Frutas
                    menu.mostrarMenuFrutas(id)
                    println("Selecciona una opcion: ")
                    opcion = readln().toInt()
                    when (opcion){
                        1 -> { //Mostrar Frutas de la Tienda
                            menu.mostrarFrutas(id)
                        }
                        2 -> { //Agregar Fruta a Tienda
                            println("Ingrese el NOMBRE DE LA FRUTA: ")
                            var nombreFruta = readln()
                            println("Ingrese el PRECIO DE LA FRUTA: ")
                            var precioFruta = readln().toDouble()
                            println("Ingrese el CANTIDAD DE LA FRUTA: ")
                            var cantidad = readln().toInt()
                            menu.agregarFruta(id, nombreFruta, precioFruta, cantidad)
                        }
                        3 -> {
                            menu.mostrarFrutas(id)
                            println("Ingrese el ID de la FRUTA a ELIMINAR: ")
                            var idFruta = readln().toInt()
                            menu.eliminarFruta(id, idFruta)
                        }
                        4 -> {
                            menu.mostrarFrutas(id)
                            println("Ingrese el ID de la FRUTA a MODIFICAR: ")
                            var idFruta = readln().toInt()
                            var fruta = menu.seleccionarFruta(id, idFruta)
                            println("\n")
                            println("**EN CASO DE NO ALTERAR UN CAMPO DEJE EN BLANCO**")
                            println("Ingrese el NOMBRE DE LA FRUTA: ")
                            var nombreFruta: String? = readln()
                            if (nombreFruta == ""){
                                nombreFruta = fruta?.nombreFruta
                            }
                            println("Ingrese el PRECIO de la FRUTA: ")
                            var precioFruta : String? = readln()
                            var precioFrutaDob : Double?
                            if (precioFruta == "" ){
                                precioFrutaDob = fruta?.precioFruta
                            } else {
                                precioFrutaDob = precioFruta?.toDouble()
                            }
                            println("Ingrese la CANTIDAD de la FRUTA: ")
                            var cantidad : String? = readln()
                            var cantidadInt : Int?
                            if (cantidad == ""){
                                cantidadInt = fruta?.cantidad
                            } else {
                                cantidadInt = cantidad?.toInt()
                            }
                            menu.modificarFruta(id, idFruta, nombreFruta, precioFrutaDob, cantidadInt)
                        }
                    }
                }
                opcion = 0
            }
            3 -> { //Agregar Tienda
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
            4-> { // Eliminar Tienda
                menu.mostrarTiendas()
                println("Ingrese el ID de la tienda a ELIMINAR: ")
                var id = readln().toInt()
                menu.eliminarTienda(id)
            }
            5 -> { // Modificar Tienda
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