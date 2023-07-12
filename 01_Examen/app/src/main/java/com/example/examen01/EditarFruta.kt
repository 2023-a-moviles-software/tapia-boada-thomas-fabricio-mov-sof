package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class EditarFruta : AppCompatActivity() {
    var arreglo = BaseDatosEnMemoria.arregloTiendas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_fruta)
        val objetoIntent : Intent = intent
        val idTienda = objetoIntent.getIntExtra("idTienda",10)
        val idFruta = objetoIntent.getIntExtra("idFruta",10)

        findViewById<TextView>(R.id.tv_nombreFrutaEdit).setText(arreglo[idTienda].frutas[idFruta].nombreFruta)
        findViewById<EditText>(R.id.txt_nombreFrutaEdit).setText(arreglo[idTienda].frutas[idFruta].nombreFruta)
        findViewById<EditText>(R.id.txt_precioFrutaEdit).setText((arreglo[idTienda].frutas[idFruta].precio).toString())
        findViewById<EditText>(R.id.txt_cantidadFrutaEdit).setText((arreglo[idTienda].frutas[idFruta].cantidad).toString())
        findViewById<EditText>(R.id.txt_familiaFrutaEdit).setText(arreglo[idTienda].frutas[idFruta].familiaFruta)

        val botonActualizarFruta = findViewById<Button>(R.id.btn_actualizarFruta)
        botonActualizarFruta.setOnClickListener {
            var nombreFruta = findViewById<EditText>(R.id.txt_nombreFrutaEdit).text.toString()
            var precioFruta = (findViewById<EditText>(R.id.txt_precioFrutaEdit).text.toString()).toDouble()
            var cantidadFruta = (findViewById<EditText>(R.id.txt_cantidadFrutaEdit).text.toString()).toInt()
            var familiaFruta = findViewById<EditText>(R.id.txt_familiaFrutaEdit).text.toString()

            arreglo[idTienda].frutas[idFruta].nombreFruta = nombreFruta
            arreglo[idTienda].frutas[idFruta].precio = precioFruta
            arreglo[idTienda].frutas[idFruta].cantidad = cantidadFruta
            arreglo[idTienda].frutas[idFruta].familiaFruta = familiaFruta

            finish()

        }

    }
}