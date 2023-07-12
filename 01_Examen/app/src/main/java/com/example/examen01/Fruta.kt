
package com.example.examen01


class Fruta (var nombreFruta: String,
             var precio: Double,
             var cantidad: Int,
             var disponibilidad: Boolean,
             var familiaFruta: String
) {
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

    override fun toString(): String {
        return "Fruta: $nombreFruta  Precio: $precio  Cantidad: $cantidad"
    }

}