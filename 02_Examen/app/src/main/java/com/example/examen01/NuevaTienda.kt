package com.example.examen01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class NuevaTienda : AppCompatActivity() {
    var arreglo = BaseDatosEnMemoria.arregloTiendas
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_tienda)
        val botonCrearTienda = findViewById<Button>(R.id.btn_crearTienda)
        botonCrearTienda.setOnClickListener {
            var nombreTienda = (findViewById<EditText>(R.id.txt_nombreTienda)).getText().toString()
            var direccion = (findViewById<EditText>(R.id.txt_direccion)).getText().toString()
            var RUC = (findViewById<EditText>(R.id.txt_ruc)).getText().toString()
            var telefono = Integer.parseInt((findViewById<EditText>(R.id.txt_telefono)).getText().toString())
            var propietario = (findViewById<EditText>(R.id.txt_propietario)).getText().toString()

            arreglo.add(Tienda(nombreTienda,direccion,RUC,telefono,propietario))

            finish()

        }
    }
}
