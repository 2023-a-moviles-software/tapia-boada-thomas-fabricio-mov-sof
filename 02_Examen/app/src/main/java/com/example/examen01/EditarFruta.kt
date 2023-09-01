package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditarFruta : AppCompatActivity() {
    var arreglo = BaseDatosEnMemoria.arregloTiendas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_fruta)
        val objetoIntent : Intent = intent
        val idTienda = objetoIntent.getStringExtra("idTienda")
        val idFruta = objetoIntent.getStringExtra("idFruta")

        consultarFrutaPorId(idTienda!!, idFruta!!)

        val botonActualizarFruta = findViewById<Button>(R.id.btn_actualizarFruta)
        botonActualizarFruta.setOnClickListener {
            var nombreFruta = findViewById<EditText>(R.id.txt_nombreFrutaEdit).text.toString()
            var precioFruta = (findViewById<EditText>(R.id.txt_precioFrutaEdit).text.toString()).toDouble()
            var cantidadFruta = (findViewById<EditText>(R.id.txt_cantidadFrutaEdit).text.toString()).toInt()
            var familiaFruta = findViewById<EditText>(R.id.txt_familiaFrutaEdit).text.toString()

        actualizarFruta(idTienda, idFruta, nombreFruta, precioFruta, cantidadFruta, familiaFruta)

            finish()

        }

    }


    fun consultarFrutaPorId(idTienda: String, idFruta: String){
        val db = Firebase.firestore
        val frutasReferencia = db.collection("tiendas").document(idTienda).collection("frutas").document(idFruta)
        frutasReferencia
            .get()
            .addOnSuccessListener {
                findViewById<TextView>(R.id.tv_nombreFrutaEdit).text = it.data?.get("nombre") as String?
                findViewById<EditText>(R.id.txt_nombreFrutaEdit).setText(it.data?.get("nombre") as String?)
                findViewById<EditText>(R.id.txt_precioFrutaEdit).setText((it.data?.get("precio") as Double?).toString())
                findViewById<EditText>(R.id.txt_cantidadFrutaEdit).setText((it.data?.get("cantidad") as Number?).toString())
                findViewById<EditText>(R.id.txt_familiaFrutaEdit).setText(it.data?.get("familia") as String?)
            }
    }

    fun actualizarFruta(
        idTienda: String,
        idFruta: String,
        nombre : String,
        precio : Double,
        cantidad : Number,
        familia : String,
        ){
        var db = Firebase.firestore
        var frutasReferencia = db.collection("tiendas").document(idTienda).collection("frutas").document(idFruta)
        frutasReferencia.set(
            hashMapOf(
                "nombre" to nombre,
                "precio" to precio,
                "cantidad" to cantidad,
                "familia" to familia,
            )
        )

    }

}