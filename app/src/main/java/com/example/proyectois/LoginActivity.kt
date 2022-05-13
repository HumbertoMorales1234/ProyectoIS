package com.example.proyectois


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(){

   lateinit var btnSiguiente : Button;
    lateinit var btnEntrar : Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login);

        btnEntrar =findViewById(R.id.button3);
        btnEntrar.setOnClickListener{
            startActivity(Intent(this,Medicinas::class.java))
        }

        btnSiguiente = findViewById(R.id.creaCuenta);
        btnSiguiente.setOnClickListener{
            startActivity(Intent(this, CrearPerfil::class.java))
        }
    }
}