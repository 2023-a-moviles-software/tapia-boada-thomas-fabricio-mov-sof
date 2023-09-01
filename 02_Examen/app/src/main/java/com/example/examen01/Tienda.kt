package com.example.examen01

class Tienda (
    var idTienda : String?,
    var nombreTienda : String?,
    var direccion : String?,
    var ruc : String?,
    var telefono : Number?,
    var propietario: String?,
    var frutas: ArrayList<Fruta> = arrayListOf <Fruta>(),
    var ventas : ArrayList<Double>,
    var ventaActual : Double

) {
    init {
        this.nombreTienda; this.direccion; this.ruc; this.telefono; this.propietario
    }

    constructor(
        idTienda : String,
        nombreTienda: String?,
        direccion: String?,
        ruc: String?,
        telefono: Number?,
        propietario: String?

    ) : this(
        idTienda,
        nombreTienda,
        direccion,
        ruc,
        telefono,
        propietario,
        frutas = ArrayList<Fruta>(),
        ventas = ArrayList<Double>(),
        ventaActual = 0.0
    )

    fun actualizarFrutas(frutas : ArrayList<Fruta>){
        this.frutas = frutas
    }


    fun añadirFruta(fruta: Fruta) {
        this.frutas.add(fruta)
    }


    fun finalizarCompra() : Double{
        this.ventas.add(ventaActual)
        var venta = ventaActual
        this.ventaActual = 0.0
        println("Compra realizada con exito")
        return venta
    }

    override fun toString(): String {
        return "$nombreTienda"
    }
}