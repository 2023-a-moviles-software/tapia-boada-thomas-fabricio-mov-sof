package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import org.w3c.dom.Text

class NuevaTienda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_tienda)

        val nombreTienda: String? = intent.getStringExtra("nombre")
        val direccion: String? = intent.getStringExtra("direccion")
        val RUC: String? = intent.getStringExtra("RUC")
        val numero : Int = intent.getIntExtra("numero",0)
        val propietario : String? = intent.getStringExtra("propietario")
        val boton = findViewById<Button>(R.id.btn_crearTienda)
        boton.setOnClickListener {devolverRespuesta()}

    }

    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros
            .putExtra("nombre",findViewById<EditText>(R.id.txt_nombreTienda).getText().toString())
            .putExtra("direccion", findViewById<EditText>(R.id.txt_direccion).getText().toString())
            .putExtra("RUC", findViewById<EditText>(R.id.txt_ruc).getText().toString())
            .putExtra("numero", Integer.parseInt(findViewById<EditText>(R.id.txt_numero).getText().toString()))
            .putExtra("propietario", findViewById<EditText>(R.id.txt_propietario).getText().toString())
            setResult(
                RESULT_OK,
                intentDevolverParametros
            )
        finish()
    }
}