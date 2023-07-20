package com.example.a02_deber

class BDMemoria {
    companion object {
        val cuenta : Cuenta = Cuenta("Thomas Tapia",100.00)
    init {
        cuenta.movimientos.add(Movimiento("Nintendo","2023/07/20","-$19.99",R.mipmap.ic_nintendo))
        cuenta.movimientos.add(Movimiento("Amazon","2023/07/20","-$20.00",R.mipmap.ic_amazon))
        cuenta.tarjetas.add(Tarjeta("Thomas Tapia", "12345678901", R.mipmap.ic_visa))
        cuenta.tarjetas.add(Tarjeta("Thomas Tapia", "09876543214",R.mipmap.ic_mastercard))
    }
    }
}