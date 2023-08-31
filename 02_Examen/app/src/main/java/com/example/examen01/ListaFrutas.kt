package com.example.examen01

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

class ListaFrutas : AppCompatActivity() {
    var arreglo = BaseDatosEnMemoria.arregloTiendas
    var adaptador : ArrayAdapter<Fruta>? = null
    var idTienda : Int = 10
    var arregloFrutas : ArrayList<Fruta>? = null
    var idItemSeleccionado = 0

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

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //Llenar las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_frutas, menu)
        //obtener el id del ArrayList seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editarFruta -> {
                intent = Intent(this,EditarFruta::class.java)
                intent.putExtra("idTienda",idTienda)
                intent.putExtra("idFruta",idItemSeleccionado)
                startActivity(intent)
                return true
            }

            R.id.mi_eliminarFruta -> {
                abrirDialogo()
                "Hacer algo con ${idItemSeleccionado}"
                return false
            }

            else -> super.onContextItemSelected(item)

        }
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea Eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener { //Callback
                    dialog, which -> eliminarFruta()
                adaptador!!.notifyDataSetChanged()
            }
        )
        builder.setNegativeButton("Cancelar", null)
        val dialogo= builder.create()
        dialogo.show()
    }


    fun eliminarFruta(){
        arregloFrutas?.removeAt(idItemSeleccionado)
    }



    override fun onResume() {
        super.onResume()
        adaptador!!.notifyDataSetChanged()
    }


}