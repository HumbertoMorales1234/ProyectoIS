package com.example.proyectois


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(){

   lateinit var btnSiguiente : Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login);

        btnSiguiente = findViewById(R.id.creaCuenta);
        btnSiguiente.setOnClickListener{
            startActivity(Intent(this, CrearPerfil::class.java))
        }
    }
}