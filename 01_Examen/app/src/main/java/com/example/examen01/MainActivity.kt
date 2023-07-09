package com.example.examen01

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var arreglo = BaseDatosEnMemoria.arregloTiendas
    var idItemSeleccionado = 0
    var adaptador : ArrayAdapter<Tienda>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // adaptador (Iterables)
        val listView = findViewById<ListView>(R.id.lv_tiendas)
        adaptador = ArrayAdapter(
            this, // contexto
            android.R.layout.simple_list_item_1, //layout.xml que se va a usar
            arreglo
        )
        listView.adapter = adaptador
        registerForContextMenu(listView)
        adaptador!!.notifyDataSetChanged()
        val botonNuevaTienda = findViewById<Button>(R.id.btn_crear)
        botonNuevaTienda.setOnClickListener {
            irActividad(NuevaTienda::class.java)
            adaptador!!.notifyDataSetChanged()
        }

    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                intent = Intent(this,EditarTienda::class.java)
                intent.putExtra("idTienda",idItemSeleccionado)
                startActivity(intent)
                return true
            }

            R.id.mi_eliminar -> {
                abrirDialogo()
                "Hacer algo con ${idItemSeleccionado}"
                return false
            }

            R.id.mi_verFrutas -> {
                "Hacer algo con ${idItemSeleccionado}"
                return false
            }

            else -> super.onContextItemSelected(item)

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
        inflater.inflate(R.menu.menu, menu)
        //obtener el id del ArrayList seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        //NO RECIBIMOS RESPUESTA
        startActivity(intent)
        //this.startActivity()
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea Eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener { //Callback
                    dialog, which -> eliminarTienda()
                    adaptador!!.notifyDataSetChanged()
            }
        )
        builder.setNegativeButton("Cancelar", null)
        val dialogo= builder.create()
        dialogo.show()
    }

    fun eliminarTienda(){
        arreglo.removeAt(idItemSeleccionado)
    }

}