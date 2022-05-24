package com.example.proyectois

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.example.proyectois.clases.Paciente
import com.example.proyectois.utils.AdapterTratamiento
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_tratamiento.*
import java.io.BufferedReader
import java.io.InputStreamReader

class TratamientoActivity : AppCompatActivity() {

    private lateinit var prefs : SharedPreferences
    private val llave = "LaLlave"
    lateinit var btnAgregar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tratamiento)

        Toast.makeText(applicationContext,"Estoy en tratamiento",Toast.LENGTH_LONG).show()
//Se busca en preferencias al usuario---------------------------------------------------------------
        prefs = PreferenceManager.getDefaultSharedPreferences(this)
        if(prefs.getInt(llave, -1)==-1){
            startActivity(Intent(this, LoginActivity::class.java))
        }else{
            val gson = Gson()
            val Pacientes = ArrayList<Paciente>()
        try {
//Se intenta leer el archivo y se obtiene el arreglo de pacientes-----------------------------------
            val file = InputStreamReader(openFileInput("pacientes.txt"))
            val bfr = BufferedReader(file)
            var linea = bfr.readLine()
            var texto = ""
            while (linea!=null){
                texto+=linea
                linea = bfr.readLine()
                                }
            val PacientesJson = gson.fromJson(texto, Array<Paciente>::class.java)
            for (n: Paciente in PacientesJson){
                Pacientes.add(n)
            }
//Si existe algo dentro del Gson se manda al adapter para obtener el view---------------------------
            if(Pacientes[prefs.getInt(llave,-1)].tratamientos != null){
                listTratamientos.adapter= Pacientes[prefs.getInt(llave,-1)].tratamientos?.let {
                    AdapterTratamiento(
                        this, R.layout.row_vitales,it
                    )
                }
            }
//--------------------------------------------------------------------------------------------------
            }catch (e:Exception){
            Log.d("Error", e.toString())
            Toast.makeText(applicationContext, "Error al cargar", Toast.LENGTH_LONG).show()
            }
        }

//Listener para el botón ---------------------------------------------------------------------------
        btnAgregar= findViewById(R.id.btnAddTrat)
        btnAgregar.setOnClickListener {
            startActivity(Intent(this, AgregarTratamiento::class.java))
        }
//--------------------------------------------------------------------------------------------------
    }
    //Configuración del Menu -----------------------------------------------------------------------
    // Crea al menu de forma visible
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    // Agrega las funcionalidades de los items del menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.signosVitales -> startActivity(Intent(this, SignosVitales::class.java))
            R.id.resumen -> startActivity(Intent(this, Resumen::class.java))
            R.id.perfilUsuario -> startActivity(Intent(this, EdicionPerfil::class.java))
            R.id.medicinas -> startActivity(Intent(this, TratamientoActivity::class.java))
            R.id.salirSesion -> salir()
        }
        return super.onOptionsItemSelected(item)
    }
    fun salir(){
        val editor = prefs.edit()
        editor.remove(llave)
        editor.apply()
        startActivity(Intent(this, LoginActivity::class.java))
    }
//--------------------------------------------------------------------------------------------------
}