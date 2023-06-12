class Tienda (
    val nombreTienda : String,
    val direccion : String,
    val ruc : String,
    val telefono : Int,
    val propietario: String,
    var frutas : ArrayList<Fruta>,
    var ventas : ArrayList<Double>,
    var ventaActual : Double

) {
    init {
        this.nombreTienda; this.direccion; this.ruc; this.telefono; this.propietario;this.frutas
    }

    constructor(
            nombreTienda: String,
            direccion: String,
            ruc: String,
            telefono: Int,
            propietario: String

    ) : this(
            nombreTienda,
            direccion,
            ruc,
            telefono,
            propietario,
            frutas = ArrayList<Fruta>(),
            ventas = ArrayList<Double>(),
            ventaActual = 0.0
    )


    fun añadirFruta(fruta: Fruta) {
        this.frutas.add(fruta)
    }

    fun eliminarFruta(numeroFruta : Int): String {
            val nombreFrutaEliminada = this.frutas.get(numeroFruta-1).nombreFruta
            this.frutas.removeAt(numeroFruta-1)
        return nombreFrutaEliminada
    }

    fun añadirCantidadFruta(numeroFruta: Int, cantidad: Int){
        if (cantidad > 0){
            this.frutas.get(numeroFruta - 1).aumentarCantidad(cantidad)
            println(this.frutas.get(numeroFruta - 1).cantidad)
        } else {
            println("No se puede gil")
        }
    }
    fun comprarFruta(numeroFruta: Int, cantidad:Int) : String{
        if (this.frutas.get(numeroFruta-1).cantidad > 0 && cantidad <= this.frutas.get(numeroFruta-1).cantidad) {
            var cantidadDisponible = this.frutas.get(numeroFruta - 1).disminuirCantidad(cantidad)
            this.ventaActual = this.ventaActual + this.frutas.get(numeroFruta - 1).precio * cantidad
            return ventaActual.toString()
        }
        else {
                return "Producto no Disponible"
        }
    }

    fun finalizarCompra() : Double{
        this.ventas.add(ventaActual)
        var venta = ventaActual
        this.ventaActual = 0.0
        println("Compra realizada con exito")
        return venta
    }

    override fun toString(): String {
        return "Tienda(nombreTienda='$nombreTienda', direccion='$direccion', ruc=$ruc, telefono=$telefono, propietario='$propietario')"
    }
}

class Fruta(
        val nombreFruta: String,
        val precio: Double,
        var cantidad: Int,
        var disponibilidad: Boolean,
        val familiaFruta: String
){
  init {
      this.nombreFruta; this.precio; this.cantidad; this.familiaFruta; this.disponibilidad
  }

    constructor(
            nombre: String,
            precio: Double,
            cantidad : Int,
            familiaFruta : String,
    ) : this(
           nombre, precio, cantidad, disponibilidad = true, familiaFruta
    ){
        if (cantidad > 0){
            this.disponibilidad = true
        } else {
            this.disponibilidad = false
        }
    }

    fun disminuirCantidad(cantidad : Int){
        this.cantidad = this.cantidad - cantidad
    }

    fun aumentarCantidad(cantidad : Int){
        this.cantidad = this.cantidad + cantidad
    }

}

