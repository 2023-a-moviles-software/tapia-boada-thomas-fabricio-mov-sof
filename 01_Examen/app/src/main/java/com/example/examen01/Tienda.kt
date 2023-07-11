package com.example.examen01

class Tienda (
    var nombreTienda : String,
    var direccion : String,
    var ruc : String,
    var telefono : Int,
    var propietario: String,
    var frutas: ArrayList<Fruta> = arrayListOf <Fruta>(),
    var ventas : ArrayList<Double>,
    var ventaActual : Double

) {
    init {
        this.nombreTienda; this.direccion; this.ruc; this.telefono; this.propietario
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

    fun actualizarFrutas(frutas : ArrayList<Fruta>){
        this.frutas = frutas
    }


    fun añadirFruta(fruta: Fruta) {
        this.frutas.add(fruta)
    }

    fun eliminarFruta(numeroFruta : Int): String {
        if (numeroFruta >= frutas.size || numeroFruta > 0) {
            val nombreFrutaEliminada = this.frutas.get(numeroFruta - 1).nombreFruta
            this.frutas.removeAt(numeroFruta - 1)
            return nombreFrutaEliminada
        } else {
            return "Número de Fruta no valido"
        }
    }

    fun añadirCantidadFruta(numeroFruta: Int, cantidad: Int) : String{
        if (numeroFruta >= frutas.size) {
            if (cantidad > 0) {
                this.frutas.get(numeroFruta - 1).aumentarCantidad(cantidad)
                return "Se agrego $cantidad de ${frutas.get(numeroFruta - 1).nombreFruta} "
            } else {
                return "El valor no es permitido"
            }
        } else {
            return "Número de Fruta no valido"
        }
    }
    fun comprarFruta(numeroFruta: Int, cantidad:Int) : String{
        if (numeroFruta >= frutas.size) {
            if (this.frutas.get(numeroFruta - 1).cantidad > 0 && cantidad <= this.frutas.get(numeroFruta - 1).cantidad) {
                var cantidadDisponible = this.frutas.get(numeroFruta - 1).disminuirCantidad(cantidad)
                this.ventaActual = this.ventaActual + this.frutas.get(numeroFruta - 1).precio * cantidad
                return ventaActual.toString()
            } else {
                return "Producto no Disponible"
            }
        } else {
            return "Número de Fruta no valido"
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
        return "$nombreTienda"
    }
}