package com.example.examen01

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.serialization.json.Json

class EditarTienda : AppCompatActivity() {
    var nombreTienda = ""
    var arrayTiendas = arrayListOf<Tienda>()
    val callbackEditTienda =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
                result ->
            if (result.resultCode == Activity.RESULT_OK){
                if (result.data != null){
                    //Logica de Negocio
                    val data =  result.data
//                    arrayTiendas = data?.getParcelableArrayListExtra("tiendas")!!
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_tienda)
        var lista = intent.getSerializableExtra("tiendas")
        findViewById<TextView>(R.id.tv_EditNombreTienda).setText(arrayTiendas.toString())
    }
}