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
        println("1. Mostrar Frutas")
        println("2. Agregar Fruta")
        println("3. Eliminar Fruta")
        println("4. Modificar Fruta")
        println("5. Salir")
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

    fun agregarFruta(idTienda: Int, nombreFruta: String, precioFruta: Double, cantidad: Int){
        baseDatos.crearFruta(idTienda, nombreFruta, precioFruta, cantidad)
    }

    fun mostrarFrutas(idTienda: Int){
        baseDatos.consultarFrutas(idTienda).forEach { println("ID FRUTA: " + it.idFruta + " NOMBRE FRUTA: " +
                it.nombreFruta + " PRECIO: " + it.precioFruta + " CANTIDAD: " + it.cantidad)}
        println("\n")
    }

    fun eliminarFruta(idTienda : Int, idFruta: Int) {
        baseDatos.eliminarFrutaPorId(idTienda, idFruta)
    }

    fun seleccionarFruta(idTienda: Int, idFruta: Int) : Fruta?{
        var fruta = baseDatos.consultarFrutaPorId(idTienda, idFruta)
        println("ID FRUTA: " + fruta?.idFruta)
        println("NOMBRE FRUTA: " + fruta?.nombreFruta)
        println("PRECIO: " + fruta?.precioFruta)
        println("CANTIDAD: " + fruta?.cantidad)
        println("\n")
        return baseDatos.consultarFrutaPorId(idTienda, idFruta)
    }

    fun modificarFruta(idTienda: Int, idFruta: Int, nuevoNombreFruta: String?, nuevoPrecioFruta: Double?, nuevaCantidad: Int?){
        baseDatos.actualizarFruta(idTienda,idFruta, nuevoNombreFruta, nuevoPrecioFruta, nuevaCantidad)
    }
}