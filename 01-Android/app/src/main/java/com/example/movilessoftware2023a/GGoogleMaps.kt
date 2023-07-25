package com.example.movilessoftware2023a

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class GGoogleMaps : AppCompatActivity() {
    private lateinit var mapa : GoogleMap
    var permisos = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ggoogle_maps)
    }

    fun marcadorQuicentro(){
        val zoom = 17f
        val quicentro = LatLng(-0.17556708490271092, -78.48014901143776)
        val titulo = "Quicentro"
        val markQuicentro = añadirMarcadores(quicentro,titulo)
        markQuicentro.tag = titulo
        moverCamaraConZoom(quicentro)
    }

    fun solicitarPermisos(){
        val contexto = this.applicationContext
        val nombrePermisoFine = android.Manifest.permission.ACCESS_FINE_LOCATION
        val nombrePermisoCoarse = android.Manifest.permission.ACCESS_COARSE_LOCATION
        val permisosFineLocation = ContextCompat.checkSelfPermission(
            contexto,
            //Permiso que se va a checkear
            nombrePermisoFine
        )
        val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
        if (tienePermisos){
            permisos = true
        } else {
            ActivityCompat.requestPermissions(
                this, //Contexto
                arrayOf(nombrePermisoFine,nombrePermisoCoarse), //Arreglo permisos
            1 //Codigo de peticion de los permisos
            )
        }
    }

    fun iniciarLogicaMapa(){
        val fragmentoMapa = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        fragmentoMapa.getMapAsync { googleMap ->
            with(googleMap){ //googleMap != NULL
                mapa = googleMap
                establecerConfiguracionMapa()
            }
        }
    }

    fun establecerConfiguracionMapa(){
        val contexto = this.applicationContext
        with(mapa){
            val permisosFineLocation = ContextCompat.checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            val tienePermisos = permisosFineLocation == PackageManager.PERMISSION_GRANTED
            if (tienePermisos){
                mapa.isMyLocationEnabled = true //tenemos permisos
                uiSettings.isMyLocationButtonEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
        }
    }

    fun añadirMarcadores(latLng: LatLng, title: String): Marker {
        return mapa.addMarker(
            MarkerOptions().position(latLng).title(title)
        )!!
    }

    fun moverCamaraConZoom(latLng: LatLng, zoom:Float = 10f){
        mapa.moveCamera(
            CameraUpdateFactory.newLatLngZoom(latLng, zoom)
        )
    }

}