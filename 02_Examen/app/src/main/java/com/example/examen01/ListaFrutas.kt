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
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListaFrutas : AppCompatActivity() {

    var arreglo : ArrayList<Fruta> = arrayListOf()
    var adaptador : ArrayAdapter<Fruta>? = null
    var idTienda : String? = ""
    var idItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_frutas)
        val objetoIntent : Intent = intent
        idTienda = objetoIntent.getStringExtra("idTienda")
        val nombreTienda = objetoIntent.getStringExtra("nombreTienda")
        val listView = findViewById<ListView>(R.id.lv_frutas)
        adaptador = ArrayAdapter(
            this, // contexto
            android.R.layout.simple_list_item_1, //layout.xml que se va a usar
            arreglo!!
        )
        listView.adapter = adaptador
        registerForContextMenu(listView)
        findViewById<TextView>(R.id.tv_nombreTiendaFrutas).setText(nombreTienda)
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
        var idFruta = arreglo.get(idItemSeleccionado).idFruta
        return when (item.itemId) {
            R.id.mi_editarFruta -> {
                intent = Intent(this,EditarFruta::class.java)
                intent.putExtra("idTienda",idTienda)
                intent.putExtra("idFruta",idFruta)
                startActivity(intent)
                return true
            }

            R.id.mi_eliminarFruta -> {
                abrirDialogo(idTienda!!, idFruta)
                return false
            }

            else -> super.onContextItemSelected(item)

        }
    }

    fun abrirDialogo(idTienda: String, idFruta: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea Eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener { //Callback
                    dialog, which -> eliminarFruta(idTienda, idFruta)
                adaptador!!.notifyDataSetChanged()
            }
        )
        builder.setNegativeButton("Cancelar", null)
        val dialogo= builder.create()
        dialogo.show()
    }


    override fun onResume() {
        super.onResume()
        adaptador!!.notifyDataSetChanged()
        consultarFrutas(adaptador!!, idTienda!!)
    }

    fun consultarFrutas(
        adaptador: ArrayAdapter<Fruta>,
        idTienda : String
    ) {
        val db = Firebase.firestore
        val frutasRefUnico = db.collection("tiendas").document(idTienda).collection("frutas")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()
        frutasRefUnico
            .get()
            .addOnSuccessListener { // it -> eso (lo que llegue)
                for (fruta in it){
                    fruta.id
                    anadirAArregloFruta(fruta)
                }
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener {
                //Errores
            }
    }

    fun anadirAArregloFruta(
        fruta: QueryDocumentSnapshot,
    ){
        //ciudad.id
        val nuevaFruta = Fruta(
            fruta.id,
            fruta.data.get("nombre") as String?,
            fruta.data.get("precio") as Double?,
            fruta.data.get("cantidad") as Number?,
            fruta.data.get("familia") as String?
        )
        arreglo.add(nuevaFruta)
    }

    fun limpiarArreglo() {arreglo.clear()}

    fun eliminarFruta(idTienda: String, idFruta: String){
        var db = Firebase.firestore
        var frutaReferencia = db.collection("tiendas").document(idTienda).collection("frutas").document(idFruta)
        frutaReferencia.delete()
        consultarFrutas(adaptador!!,idTienda)
    }

}