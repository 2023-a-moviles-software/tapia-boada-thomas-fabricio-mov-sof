package com.example.examen01

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class EditarTienda : AppCompatActivity() {
    var arreglo = BaseDatosEnMemoria.arregloTiendas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_tienda)
        val objetoIntent : Intent = intent
        var idTienda = objetoIntent.getIntExtra("idTienda",10)
        findViewById<TextView>(R.id.tv_tituloNombreTienda).setText(arreglo.get(idTienda).nombreTienda)
        findViewById<EditText>(R.id.txt_nombreTiendaEdit).setText(arreglo.get(idTienda).nombreTienda)
        findViewById<EditText>(R.id.txt_direccionEdit).setText(arreglo.get(idTienda).direccion)
        findViewById<EditText>(R.id.txt_RUCEdit).setText(arreglo.get(idTienda).ruc)
        findViewById<EditText>(R.id.txt_telefonoEdit).setText((arreglo.get(idTienda).telefono.toString()))
        findViewById<EditText>(R.id.txt_propietarioEdit).setText(arreglo.get(idTienda).propietario)

        val botonActualizarTienda = findViewById<Button>(R.id.btn_ActualizarTienda)
        botonActualizarTienda.setOnClickListener {
            var nombreTiendaAct = findViewById<EditText>(R.id.txt_nombreTiendaEdit).text.toString()
            var direccionAct = findViewById<EditText>(R.id.txt_direccionEdit).text.toString()
            var RUCAct = findViewById<EditText>(R.id.txt_RUCEdit).text.toString()
            var telefonoAct = Integer.parseInt(findViewById<EditText>(R.id.txt_telefonoEdit).text.toString())
            var propietarioAct = findViewById<EditText>(R.id.txt_propietarioEdit).text.toString()
            arreglo.get(idTienda).nombreTienda = nombreTiendaAct
            arreglo.get(idTienda).direccion = direccionAct
            arreglo.get(idTienda).ruc = RUCAct
            arreglo.get(idTienda).telefono = telefonoAct
            arreglo.get(idTienda).propietario = propietarioAct

            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

        }
    }
}