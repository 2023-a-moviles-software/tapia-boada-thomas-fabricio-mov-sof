package com.example.examen01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Date
import kotlin.math.log

class NuevaTienda : AppCompatActivity() {
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

            crearTienda(nombreTienda,direccion, RUC,telefono,propietario)

            finish()

        }
    }

    fun crearTienda(
        nombre : String,
        direccion : String,
        ruc : String,
        telefono : Number,
        propietario : String
    ) {
        val db = Firebase.firestore
        val referenciaTienda = db
            .collection("tiendas")
        val datosTienda = hashMapOf(
            "nombre" to nombre,
            "direccion" to direccion,
            "ruc" to ruc,
            "telefono" to telefono,
            "propietario" to propietario
        )
        referenciaTienda
            .add(datosTienda)
            .addOnSuccessListener {  }
            .addOnFailureListener {  }
    }
}
