import kotlinx.serialization.Serializable

@Serializable
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
