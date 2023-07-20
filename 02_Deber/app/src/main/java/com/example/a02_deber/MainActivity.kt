package com.example.a02_deber

import android.app.UiModeManager.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var cuenta = BDMemoria.cuenta
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.dark_blue)
        var saldoCuenta = cuenta.saldo
        findViewById<TextView>(R.id.tv_saldo).text = saldoCuenta.toString()
        inicializarRecyclerViewMovimientos()
        inicializarReciclerViewTarjetas()
    }

    fun inicializarRecyclerViewMovimientos(){
        val recyclerView = findViewById<RecyclerView>(
            R.id.rv_movimientosRecientes
        )
        val adaptador =AdaptadorMovimientosRecientes(
            this,
            cuenta.movimientos.reversed() as ArrayList<Movimiento>,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }

    fun inicializarReciclerViewTarjetas(){
        val recyclerView = findViewById<RecyclerView>(
            R.id.rv_cartera
        )
        val adaptador =AdaptadorTarjetas(
            this,
            cuenta.tarjetas,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        adaptador.notifyDataSetChanged()
    }

}