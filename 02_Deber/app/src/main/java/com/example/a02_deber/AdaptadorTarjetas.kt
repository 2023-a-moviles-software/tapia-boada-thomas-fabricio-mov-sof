package com.example.a02_deber

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextClock
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorTarjetas (
    private val contexto :MainActivity,
    private val lista : ArrayList<Tarjeta>,
    private val recyclerView: RecyclerView
        ): RecyclerView.Adapter<AdaptadorTarjetas.MyViewHolder>(){
    inner class MyViewHolder(view : View) :RecyclerView.ViewHolder(view) {
        val imagenTipoTarjeta : ImageView
        val numeroTarjeta : TextView
        val nombreTarjeta : TextView
         init {
             imagenTipoTarjeta = view.findViewById(R.id.img_tipoTarjeta)
             numeroTarjeta = view.findViewById(R.id.tv_numeroTarjeta)
             nombreTarjeta = view.findViewById(R.id.tv_nombreTarjeta)
         }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdaptadorTarjetas.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recyclerview_vista_tarjetas,
            parent,
            false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tarjetaActual = this.lista[position]
        holder.nombreTarjeta.text = tarjetaActual.nombrePropietario
        holder.numeroTarjeta.text = tarjetaActual.numeroTarjeta
        holder.imagenTipoTarjeta.setImageResource(tarjetaActual.imagenTarjeta)

    }

    override fun getItemCount(): Int {
        return this.lista.size
    }



}