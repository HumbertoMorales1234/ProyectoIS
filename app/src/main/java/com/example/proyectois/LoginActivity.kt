package com.example.proyectois


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity(){

    private lateinit var btnSiguiente : Button
    private lateinit var btnEntrar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnEntrar =findViewById(R.id.button3)
        btnEntrar.setOnClickListener{
            startActivity(Intent(this,EdicionPerfil::class.java))
        }

        btnSiguiente = findViewById(R.id.creaCuenta)
        btnSiguiente.setOnClickListener{
            startActivity(Intent(this, CrearPerfil::class.java))
        }
    }
}