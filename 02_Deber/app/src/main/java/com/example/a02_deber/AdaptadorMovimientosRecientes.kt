package com.example.a02_deber

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class AdaptadorMovimientosRecientes (
    private val contexto :MainActivity,
    private val lista : ArrayList<Movimiento>,
    private val recyclerView: RecyclerView
        ) : RecyclerView.Adapter<AdaptadorMovimientosRecientes.MyViewHolder>(){
            inner class MyViewHolder(view : View) :RecyclerView.ViewHolder(view){
            val imagenImageView: ShapeableImageView
            val nombreMovimientoTextView : TextView
            val montoMovimientoTextView : TextView
            val fechaMovimientoTextView : TextView
             init {
                imagenImageView = view.findViewById(R.id.img_imagenMovimiento)
                nombreMovimientoTextView = view.findViewById(R.id.tv_nombreMovimiento)
                montoMovimientoTextView = view.findViewById(R.id.tv_saldoActual)
                fechaMovimientoTextView = view.findViewById(R.id.tv_fechaMovimiento)
             }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recyclerview_vista_movimientos,
            parent,
            false
        )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movimientoActual = this.lista[position]
        holder.nombreMovimientoTextView.text = movimientoActual.nombre
        holder.fechaMovimientoTextView.text = movimientoActual.fecha
        holder.montoMovimientoTextView.text = "$${movimientoActual.monto}"
        holder.imagenImageView.setImageResource(movimientoActual.imagen)
    }
}