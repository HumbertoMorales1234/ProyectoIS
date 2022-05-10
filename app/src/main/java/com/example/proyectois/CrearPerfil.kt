package com.example.proyectois

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button


class CrearPerfil : AppCompatActivity() {

        lateinit var salir : Button;
        lateinit var arregloSangres : AutoCompleteTextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_perfil)

        val sangres = resources.getStringArray(R.array.tiposSanguineos);
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_tipos_sanguineos, sangres)
        arregloSangres= findViewById(R.id.seleccionSanguineoCreaP)
        arregloSangres.setAdapter(arrayAdapter);

        salir = findViewById(R.id.salirCrear);
        salir.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}