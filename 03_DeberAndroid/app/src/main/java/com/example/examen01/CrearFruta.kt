package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class CrearFruta : AppCompatActivity() {
    var arreglo = BaseDatosEnMemoria.arregloTiendas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_fruta)
        var objetoIntent : Intent = intent
        val idTienda = objetoIntent.getIntExtra("idTiendaFruta",10)
        val botonCrearFrutaTienda = findViewById<Button>(R.id.btn_crearFrutaTienda)

        botonCrearFrutaTienda.setOnClickListener {
            var nombreFruta = findViewById<EditText>(R.id.txt_nombreFruta).text.toString()
            var precioFruta = (findViewById<EditText>(R.id.txt_precio).text.toString()).toDouble()
            var cantidadFruta = Integer.parseInt(findViewById<EditText>(R.id.txt_cantidad).text.toString())
            var familiaFruta = findViewById<EditText>(R.id.txt_familiaFruta).text.toString()

//            arreglo[idTienda].frutas.add(Fruta(nombreFruta,precioFruta,cantidadFruta,familiaFruta))

            finish()
        }


    }
}