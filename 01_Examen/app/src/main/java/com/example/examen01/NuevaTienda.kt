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
        val boton = findViewById<Button>(R.id.btn_crearTienda)
        boton.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.txt_nombreTienda)
            val direccion = findViewById<EditText>(R.id.txt_direccion)
            val ruc = findViewById<EditText>(R.id.txt_ruc)
            val propietario = findViewById<EditText>(R.id.txt_propietario)

            BaseDatos.tablaTiendas!!.crearTienda(
                nombre.text.toString(),
                direccion.text.toString(),
                ruc.text.toString(),
                propietario.text.toString()
            )
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}
