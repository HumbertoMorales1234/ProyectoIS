package com.example.proyectois

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.proyectois.clases.AlarmReceiver
import com.example.proyectois.clases.Medicamento
import com.example.proyectois.clases.Paciente
import com.example.proyectois.clases.Tratamiento
import com.example.proyectois.utils.AdapterMedicinas
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_medicinas.*
import kotlinx.android.synthetic.main.activity_tratamiento.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

class Medicinas : AppCompatActivity() {
    lateinit var btnAgregarMed: Button
    lateinit var lblTrat : TextView
    lateinit var tratamientoActual : Tratamiento
    lateinit var arrayMedicinasActual : ArrayList<Medicamento>
    private lateinit var prefs : SharedPreferences
    private val llave = "LaLlave"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicinas)

        val nombreTrat = intent.getStringExtra("nombreTrat")
        val fechaTrat = intent.getStringExtra("fechaTrat")
        val index = intent.getStringExtra("index")


        lblTrat = findViewById(R.id.nombreTratamientoMedicinas)
        lblTrat.text="Tratamiento: "+nombreTrat


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

                var arrayTratamientos = Pacientes[prefs.getInt(llave,-1)].tratamientos

                if (arrayTratamientos != null) {
                    for(n: Tratamiento in arrayTratamientos){
                        if(n.nombre==nombreTrat && n.fecha==fechaTrat){
                            tratamientoActual=n

                        }

                    }
                }else{
                    Toast.makeText(applicationContext, "Error al cargar", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this,TratamientoActivity::class.java))
                }
                   if (tratamientoActual.medicinas!=null){
                        arrayMedicinasActual = tratamientoActual.medicinas!!
                        listMedicinas.adapter = arrayMedicinasActual.let {
                            AdapterMedicinas(this,R.layout.row_medicinas,it)
                        }

                    }else{}

//--------------------------------------------------------------------------------------------------
            }catch (e:Exception){
                Log.d("Error", e.toString())
                Toast.makeText(applicationContext, "Error al cargar", Toast.LENGTH_LONG).show()
            }




        }




//--------------------------------------------------------------------------------------------------

//Botón de agregar medicina-------------------------------------------------------------------------
        btnAgregarMed = findViewById(R.id.botonAddMedicina)
        btnAgregarMed.setOnClickListener {
            val next= Intent(this,AgregarMedicina::class.java)
            next.putExtra("nombreTrat",nombreTrat)
            next.putExtra("fechaTrat",fechaTrat)
            next.putExtra("index",index)
            startActivity(next)
        }
//--------------------------------------------------------------------------------------------------


    }

//Configuración del Menu ---------------------------------------------------------------------------
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

        //Construcción de la notificación




    }
