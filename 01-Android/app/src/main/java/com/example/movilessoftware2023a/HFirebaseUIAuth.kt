package com.example.movilessoftware2023a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class HFirebaseUIAuth : AppCompatActivity() {

    //Callback del INTENT de LOGIN
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract())
    {res : FirebaseAuthUIAuthenticationResult ->
        if (res.resultCode === RESULT_OK) {
            if (res.idpResponse != null){
                //Logica de negocio
                seLogeo(res.idpResponse!!)
            }
        }
    }

    fun seLogeo(
        res : IdpResponse
    ){
        val btnLogin : Button = findViewById<Button>(R.id.btn_login_firebase)
        val btnLogout = findViewById<Button>(R.id.btn_logout_firebase)
        btnLogin.visibility = View.INVISIBLE
        btnLogout.visibility = View.VISIBLE
        if(res.isNewUser == true){
            registrarUsuarioPorPrimeraVez(res)
        }
    }

    fun registrarUsuarioPorPrimeraVez(usuario : IdpResponse ){/*usuario.email; usuario.phoneNumber; usuario.user.name; */}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hfirebase_uiauth)



    }
}