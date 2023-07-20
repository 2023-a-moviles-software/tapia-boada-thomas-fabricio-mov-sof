package com.example.a02_deber

class Cuenta (
    var nombreCuenta : String,
    var movimientos : ArrayList<Movimiento>,
    var tarjetas : ArrayList<Tarjeta>,
    var saldo : Double
        ) {
    constructor(
        nombreCuenta: String,
        saldo: Double
    ) : this(
        nombreCuenta,
        movimientos = ArrayList<Movimiento>(),
        tarjetas = ArrayList<Tarjeta>(),
        saldo
    )
}