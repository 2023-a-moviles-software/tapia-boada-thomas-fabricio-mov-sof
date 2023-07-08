package com.example.examen01
import kotlinx.serialization.Serializable

@Serializable
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
            return "Tienda(nombreTienda='$nombreTienda', direccion='$direccion', ruc=$ruc, telefono=$telefono, propietario='$propietario')"
        }
}
