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
        lateinit var arregloCronos : AutoCompleteTextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_perfil)

        // Configuracion del array adapter de tipos sanguineos - Esto es para que haga funcion de cajita
        val sangres = resources.getStringArray(R.array.tiposSanguineos);
        val arrayAdapterSangre = ArrayAdapter(this, R.layout.dropdown_tipos_sangre, sangres)
        arregloSangres= findViewById(R.id.seleccionSanguineoCreaP)
        arregloSangres.setAdapter(arrayAdapterSangre);

        //Array adapter de enfermedades crónicas.
        val cronicos = resources.getStringArray(R.array.padecimientosCronicos)
        val arrayAdapterCronicos = ArrayAdapter(this, R.layout.dropdown_tipos_cronicos, cronicos)
        arregloCronos = findViewById(R.id.seleccionCronicoCreaP)
        arregloCronos.setAdapter(arrayAdapterCronicos);

        //Configuracion de boton de salir de la pantalla de crear perfil
        salir = findViewById(R.id.salirCrear);
        salir.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}