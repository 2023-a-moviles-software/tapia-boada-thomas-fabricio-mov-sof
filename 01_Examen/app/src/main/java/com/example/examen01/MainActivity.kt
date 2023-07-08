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
    var arrayTiendas = arrayListOf<Tienda>()
    var idItemSeleccionado = 0
    var nombreTienda : String = ""
    var direccion : String = ""
    var RUC : String = ""
    var numero : Int = 0
    var propietario : String = ""
    var adaptador : ArrayAdapter<Tienda>? = null
    var listView : ListView? = null
    lateinit var db : BaseDatosHelper

    val callbackCreacionTienda =
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
                    añadirTienda(arrayTiendas)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = BaseDatosHelper(this)


        listView = findViewById<ListView>(R.id.lv_tiendas)
        adaptador = ArrayAdapter(
            this, // contexto
            android.R.layout.simple_list_item_1, //layout.xml que se va a usar
            arrayTiendas
        )
        listView!!.adapter = adaptador
        adaptador!!.notifyDataSetChanged()
        val botonAñadirTienda = findViewById<Button>(R.id.btn_crear)
        botonAñadirTienda.setOnClickListener{
           irActividad(NuevaTienda::class.java)
//            arrayTiendas = BaseDatos.tablaTiendas!!.mostrarDatos()!!
//            adaptador!!.notifyDataSetChanged()

        }
        registerForContextMenu(listView)
    }

    override fun onResume() {
        super.onResume()
        listView!!.adapter = adaptador
        adaptador!!.notifyDataSetChanged()
    }


    fun añadirTienda(adaptador: ArrayList<Tienda>){
        arrayTiendas.add(Tienda(nombreTienda,direccion, RUC ,propietario))
    }

    fun eliminarTienda(adaptador: ArrayList<Tienda>){
        arrayTiendas.removeAt(idItemSeleccionado)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.mi_editar -> {
                "Hacer algo con ${idItemSeleccionado}"
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
                    dialog, which -> eliminarTienda(arrayTiendas)
                    adaptador!!.notifyDataSetChanged()
            }
        )
        builder.setNegativeButton("Cancelar", null)
        val dialogo= builder.create()
        dialogo.show()
    }

}