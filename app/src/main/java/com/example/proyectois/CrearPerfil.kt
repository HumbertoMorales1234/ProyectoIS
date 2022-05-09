package com.example.proyectois

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CrearPerfil : AppCompatActivity() {

        lateinit var salir : Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_perfil)

        salir = findViewById(R.id.salirCrear);
        salir.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}