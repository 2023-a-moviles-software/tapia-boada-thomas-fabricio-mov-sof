package com.example.a02_deber

import android.app.UiModeManager.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
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
        findViewById<TextView>(R.id.tv_saldoActual).text = "$${cuenta.saldo}"
        inicializarRecyclerViewMovimientos()
        inicializarReciclerViewTarjetas()
        val botonEnviar = findViewById<Button>(R.id.btn_enviar)
        botonEnviar.setOnClickListener {
            cuenta.movimientos.add(Movimiento("Amazon","2023/07/20",-25.55,R.mipmap.ic_amazon))
            enviar(25.55)
            inicializarRecyclerViewMovimientos()
            inicializarReciclerViewTarjetas()
            findViewById<TextView>(R.id.tv_saldoActual).text = "$${cuenta.saldo}"
        }
        val botonSolicitar = findViewById<Button>(R.id.btn_solicitar)
        botonSolicitar.setOnClickListener {
            cuenta.movimientos.add(Movimiento("Nintendo","2023/07/20",18.23,R.mipmap.ic_nintendo))
            solicitar(18.23)
            inicializarRecyclerViewMovimientos()
            inicializarReciclerViewTarjetas()
            findViewById<TextView>(R.id.tv_saldoActual).text = "$${cuenta.saldo}"
        }
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

    fun enviar(monto: Double){
        cuenta.saldo = cuenta.saldo - monto
    }

    fun solicitar(monto: Double){
        cuenta.saldo = cuenta.saldo + monto
    }

}