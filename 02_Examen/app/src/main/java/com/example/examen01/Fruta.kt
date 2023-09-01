
package com.example.examen01


class Fruta (
    var idFruta: String,
    var nombreFruta: String?,
    var precio: Double?,
    var cantidad: Number?,
    var disponibilidad: Boolean?,
    var familiaFruta: String?
) {
    init {
        this.nombreFruta; this.precio; this.cantidad; this.familiaFruta; this.disponibilidad
    }

    constructor(
        idFruta : String,
        nombre: String?,
        precio: Double?,
        cantidad : Number?,
        familiaFruta : String?,
    ) : this(
        idFruta, nombre, precio, cantidad, disponibilidad = true, familiaFruta
    ){

    }


    override fun toString(): String {
        return "Fruta: $nombreFruta  Precio: $precio  Cantidad: $cantidad"
    }

}