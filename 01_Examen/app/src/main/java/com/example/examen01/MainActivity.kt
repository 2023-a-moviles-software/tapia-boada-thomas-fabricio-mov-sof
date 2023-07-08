package com.example.examen01

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.view.View.OnCreateContextMenuListener
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var arrayTiendas = arrayListOf<Tienda>()
    var idItemSeleccionado = 0
    var nombreTienda : String = ""
    var direccion : String = ""
    var RUC : String = ""
    var numero : Int = 0
    var propietario : String = ""

    val callbackContenidoIntentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
                result ->
            if (result.resultCode == Activity.RESULT_OK){
                if (result.data != null){
                    //Logica de Negocio
                    val data =  result.data
                    nombreTienda = data?.getStringExtra("nombre").toString()
                    direccion = data?.getStringExtra("direccion").toString()
                    RUC = data?.getStringExtra("RUC").toString()
                    numero = data?.getIntExtra("numero",0)!!
                    propietario = data?.getStringExtra("propietario").toString()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.lv_tiendas)
        val adaptador = ArrayAdapter(
            this, // contexto
            android.R.layout.simple_list_item_1, //layout.xml que se va a usar
            arrayTiendas
        )
        listView.adapter = adaptador
        val botonAñadirTienda = findViewById<Button>(R.id.btn_crear)
        botonAñadirTienda.setOnClickListener{
            callbackContenidoIntentExplicito
                .launch(Intent(this, NuevaTienda::class.java))
            añadirTienda(adaptador)
            adaptador.notifyDataSetChanged()
        }
        registerForContextMenu(listView)
    }

    fun añadirTienda( adaptador : ArrayAdapter<Tienda>){
        arrayTiendas.add(Tienda(nombreTienda,direccion, RUC ,numero,propietario))
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                "Hacer algo con ${idItemSeleccionado}"
                return true
            }

            R.id.mi_eliminar -> {
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




}