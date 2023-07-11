package com.example.examen01

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class ListaFrutas : AppCompatActivity() {
    var arreglo = BaseDatosEnMemoria.arregloTiendas
    var adaptador : ArrayAdapter<Fruta>? = null
    var idTienda : Int = 10
    var arregloFrutas : ArrayList<Fruta>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_frutas)
        val objetoIntent : Intent = intent
        idTienda = objetoIntent.getIntExtra("idTiendaListaFrutas",3)
        val listView = findViewById<ListView>(R.id.lv_frutas)
        arregloFrutas = arreglo.get(idTienda).frutas
        adaptador = ArrayAdapter(
            this, // contexto
            android.R.layout.simple_list_item_1, //layout.xml que se va a usar
            arregloFrutas!!
        )
        listView.adapter = adaptador
        registerForContextMenu(listView)
        findViewById<TextView>(R.id.tv_nombreTiendaFrutas).setText(arreglo.get(idTienda).nombreTienda)
        var botonCrearFruta = findViewById<Button>(R.id.btn_crearFruta)
        botonCrearFruta.setOnClickListener {
            intent = Intent(this, CrearFruta::class.java )
            intent.putExtra("idTiendaFruta", idTienda)
            startActivity(intent)
            adaptador!!.notifyDataSetChanged()
        }

    }

    override fun onResume() {
        super.onResume()
        adaptador!!.notifyDataSetChanged()
    }


}