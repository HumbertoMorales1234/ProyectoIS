package com.example.proyectois

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_agregar_tratamiento.*

class AgregarTratamiento : AppCompatActivity() {
    private lateinit var btnGuardar : Button
    private lateinit var btnCancelar : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_tratamiento)

        btnCancelar= findViewById(R.id.btnCancelarTrat)
        btnCancelar.setOnClickListener {
            startActivity(Intent(this,TratamientoActivity::class.java))
        }

        btnGuardar= findViewById(R.id.btnGuardarTrat)
        btnGuardar.setOnClickListener {

        }

    }
}