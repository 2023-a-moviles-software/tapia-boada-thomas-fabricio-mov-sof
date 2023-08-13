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
        var tienda = BaseDatosSQLite.tablaTiendas!!.consultarTiendaPorId(idTienda)
        findViewById<TextView>(R.id.tv_tituloNombreTienda).setText(tienda.nombreTienda)
        findViewById<EditText>(R.id.txt_nombreTiendaEdit).setText(tienda.nombreTienda)
        findViewById<EditText>(R.id.txt_RUCEdit).setText(tienda.ruc)
        findViewById<EditText>(R.id.txt_telefonoEdit).setText((tienda.telefono.toString()))
        findViewById<EditText>(R.id.txt_propietarioEdit).setText((tienda.nombrePropietario))

        val botonActualizarTienda = findViewById<Button>(R.id.btn_ActualizarTienda)
        botonActualizarTienda.setOnClickListener {
            var nombreTiendaAct = findViewById<EditText>(R.id.txt_nombreTiendaEdit).text.toString()
            var RUCAct = findViewById<EditText>(R.id.txt_RUCEdit).text.toString()
            var telefonoAct = Integer.parseInt(findViewById<EditText>(R.id.txt_telefonoEdit).text.toString())
            var propietarioAct = findViewById<EditText>(R.id.txt_propietarioEdit).text.toString()
//
            BaseDatosSQLite.tablaTiendas!!.actualizarTiendaPorId(idTienda, nombreTiendaAct, RUCAct, telefonoAct, propietarioAct)

            MainActivity.tiendas = BaseDatosSQLite.tablaTiendas!!.consultarTiendas()

            finish()

        }
    }
}