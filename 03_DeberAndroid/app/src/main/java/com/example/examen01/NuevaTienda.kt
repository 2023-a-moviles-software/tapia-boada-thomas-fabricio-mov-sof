package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class NuevaTienda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_tienda)
        val botonCrearTienda = findViewById<Button>(R.id.btn_crearTienda)
        botonCrearTienda.setOnClickListener {
            var nombreTienda = (findViewById<EditText>(R.id.txt_nombreTienda)).getText().toString()
            var RUC = (findViewById<EditText>(R.id.txt_ruc)).getText().toString()
            var telefono = Integer.parseInt((findViewById<EditText>(R.id.txt_telefono)).getText().toString())
            var propietario = (findViewById<EditText>(R.id.txt_propietario)).getText().toString()

            BaseDatosSQLite.tablaTiendas!!.crearTienda(nombreTienda, RUC, telefono, propietario)
//

            finish()

        }
    }
}
