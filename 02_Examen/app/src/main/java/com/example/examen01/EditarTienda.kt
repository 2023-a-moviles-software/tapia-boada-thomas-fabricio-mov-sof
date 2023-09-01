package com.example.examen01

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditarTienda : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_tienda)
        val objetoIntent : Intent = intent
        var idTienda = objetoIntent.getStringExtra("idTienda")
        consultarTiendaPorId(idTienda!!)

        val botonActualizarTienda = findViewById<Button>(R.id.btn_ActualizarTienda)
        botonActualizarTienda.setOnClickListener {
            var nombreTiendaAct = findViewById<EditText>(R.id.txt_nombreTiendaEdit).text.toString()
            var direccionAct = findViewById<EditText>(R.id.txt_direccionEdit).text.toString()
            var RUCAct = findViewById<EditText>(R.id.txt_RUCEdit).text.toString()
            var telefonoAct = Integer.parseInt(findViewById<EditText>(R.id.txt_telefonoEdit).text.toString())
            var propietarioAct = findViewById<EditText>(R.id.txt_propietarioEdit).text.toString()
            actualizarTienda(idTienda,nombreTiendaAct,direccionAct,RUCAct,telefonoAct,propietarioAct)
            finish()

        }
    }

    fun consultarTiendaPorId(idTienda : String){
        val db = Firebase.firestore
        val tiendaReferencia = db.collection("tiendas")
        tiendaReferencia
            .document(idTienda)
            .get()
            .addOnSuccessListener {
                findViewById<TextView>(R.id.tv_tituloNombreTienda).text = it.data?.get("nombre") as String?
                findViewById<EditText>(R.id.txt_nombreTiendaEdit).setText(it.data?.get("nombre") as String?)
                findViewById<EditText>(R.id.txt_direccionEdit).setText(it.data?.get("direccion") as String?)
                findViewById<EditText>(R.id.txt_RUCEdit).setText(it.data?.get("ruc") as String?)
                findViewById<EditText>(R.id.txt_telefonoEdit).setText((it.data?.get("telefono") as Number?).toString())
                findViewById<EditText>(R.id.txt_propietarioEdit).setText(it.data?.get("propietario") as String?)
            }
    }

    fun actualizarTienda(idTienda: String ,nombre: String, direccion: String, ruc: String, telefono: Number, propietario: String ){
        var db = Firebase.firestore
        var tiendasReferencia = db.collection("tiendas").document(idTienda)
        tiendasReferencia.set(
            hashMapOf(
                "nombre" to nombre,
                "direccion" to direccion,
                "ruc" to ruc,
                "telefono" to telefono,
                "propietario" to propietario
            )
        )

    }
}