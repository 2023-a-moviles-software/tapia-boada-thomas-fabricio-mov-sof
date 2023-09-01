package com.example.examen01

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    var query: Query? = null
    val arreglo: ArrayList<Tienda> = arrayListOf()
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
//        consultarTiendas(adaptador!!)
        registerForContextMenu(listView)
        adaptador!!.notifyDataSetChanged()
        val botonNuevaTienda = findViewById<Button>(R.id.btn_crear)
        botonNuevaTienda.setOnClickListener {
            irActividad(NuevaTienda::class.java)
            adaptador!!.notifyDataSetChanged()
        }

    }

    override fun onResume() {
        super.onResume()
        consultarTiendas(adaptador!!)
        adaptador!!.notifyDataSetChanged()
    }

    fun consultarTiendas(
        adaptador: ArrayAdapter<Tienda>
    ) {
        val db = Firebase.firestore
        val tiendasRefUnico = db.collection("tiendas")
        limpiarArreglo()
        adaptador.notifyDataSetChanged()
        tiendasRefUnico
            .get()
            .addOnSuccessListener { // it -> eso (lo que llegue)
                for (tienda in it){
                    tienda.id
                    anadirAArregloTienda(tienda)
                }
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener {
                //Errores
            }
    }

    fun anadirAArregloTienda(
        tienda: QueryDocumentSnapshot,
    ){
        //ciudad.id
        val nuevaTienda = Tienda(
            tienda.id,
            tienda.data.get("nombre") as String?,
            tienda.data.get("direccion") as String?,
            tienda.data.get("ruc") as String?,
            tienda.data.get("telefono") as Number?,
            tienda.data.get("propietario") as String?
        )
        arreglo.add(nuevaTienda)
    }



    fun limpiarArreglo() {arreglo.clear()}


    override fun onContextItemSelected(item: MenuItem): Boolean {
        var idTienda = arreglo.get(idItemSeleccionado).idTienda
        var nombreTienda = arreglo.get(idItemSeleccionado).nombreTienda
        return when (item.itemId) {
            R.id.mi_editar -> {
                intent = Intent(this,EditarTienda::class.java)
                intent.putExtra("idTienda",idTienda)
                startActivity(intent)
                return true
            }

            R.id.mi_eliminar -> {
                abrirDialogo(idTienda!!)
                "Hacer algo con ${idItemSeleccionado}"
                return false
            }

            R.id.mi_verFrutas -> {
                intent = Intent(this,ListaFrutas::class.java)
                intent.putExtra("idTienda",idTienda)
                intent.putExtra("nombreTienda", nombreTienda)
                startActivity(intent)
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

    fun abrirDialogo(idTienda: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea Eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener { //Callback
                    dialog, which -> eliminarTienda(idTienda)
            }
        )
        builder.setNegativeButton("Cancelar", null)
        val dialogo= builder.create()
        dialogo.show()
    }

    fun eliminarTienda(idTienda : String){
        var db = Firebase.firestore
        var tiendasReferencia = db.collection("tiendas")
        tiendasReferencia
            .document(idTienda)
            .delete()
        consultarTiendas(adaptador!!)
    }

}