package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearFruta : AppCompatActivity() {
    var arreglo = BaseDatosEnMemoria.arregloTiendas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_fruta)
        var objetoIntent : Intent = intent
        val idTienda = objetoIntent.getStringExtra("idTiendaFruta")
        val botonCrearFrutaTienda = findViewById<Button>(R.id.btn_crearFrutaTienda)

        botonCrearFrutaTienda.setOnClickListener {
            var nombreFruta = findViewById<EditText>(R.id.txt_nombreFruta).text.toString()
            var precioFruta = (findViewById<EditText>(R.id.txt_precio).text.toString()).toDouble()
            var cantidadFruta = Integer.parseInt(findViewById<EditText>(R.id.txt_cantidad).text.toString())
            var familiaFruta = findViewById<EditText>(R.id.txt_familiaFruta).text.toString()

            crearFruta(idTienda!!, nombreFruta, precioFruta, cantidadFruta, familiaFruta)

            finish()
        }

    }

    fun crearFruta(
        idTienda : String,
        nombre : String,
        precio : Double,
        cantidad : Number,
        familia : String,
    ) {
        val db = Firebase.firestore
        val referenciaTienda = db
            .collection("tiendas").document(idTienda).collection("frutas")
        val datosTienda = hashMapOf(
            "nombre" to nombre,
            "precio" to precio,
            "cantidad" to cantidad,
            "familia" to familia,
        )
        referenciaTienda
            .add(datosTienda)
            .addOnSuccessListener {  }
            .addOnFailureListener {  }
    }

}