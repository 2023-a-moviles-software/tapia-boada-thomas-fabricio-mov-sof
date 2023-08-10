class Menu (
    val baseDatos: SQLite = SQLite()
    ){

    fun mostrarMenuTiendas(){
        println("1. Mostrar Tiendas")
        println("2. Seleccionar Tienda")
        println("3. Agregar Tienda")
        println("4. Eliminar Tienda")
        println("5. Modificar Tienda")
        println("6. Salir")
    }

    fun mostrarMenuFrutas(idTienda : Int){
        
    }

    fun mostrarTiendas(){
        baseDatos.consultarTiendas().forEach{ println("ID TIENDA:" + it.idTienda + " NOMBRE TIENDA: " + it.nombreTienda) }
        println("\n")
    }

    fun seleccionarTienda(idTienda : Int) : Tienda?{
        var tienda = baseDatos.consultarTiendasPorId(idTienda)
        println("ID TIENDA: " + tienda?.idTienda)
        println("NOMBRE TIENDA: " + tienda?.nombreTienda)
        println("RUC: " + tienda?.ruc)
        println("TELEFONO: " + tienda?.telefono)
        println("NOMBRE PROPIETARIO: " + tienda?.nombrePropietario)
        println("\n")
        return baseDatos.consultarTiendasPorId(idTienda)
    }

    fun agregarTienda(idTienda: Int, nombreTienda: String , ruc : String, telefono : Int, nombrePropietario: String){
        baseDatos.crearTienda(idTienda, nombreTienda, ruc, telefono, nombrePropietario)
    }

    fun eliminarTienda(idTienda : Int){
        baseDatos.eliminarTiendaPorId(idTienda)
    }

    fun modificarTienda(idTienda: Int, nombreTienda: String? , ruc : String?, telefono : Int?, nombrePropietario: String?){
        baseDatos.actualizarTienda(idTienda, nombreTienda, ruc , telefono , nombrePropietario)
    }

}