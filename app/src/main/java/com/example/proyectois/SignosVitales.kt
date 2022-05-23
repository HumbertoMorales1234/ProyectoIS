package com.example.proyectois

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.example.proyectois.clases.Paciente
import com.example.proyectois.clases.SignoVital
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_signos_vitales.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception


class SignosVitales : AppCompatActivity() {

    private lateinit var arregloSignos : AutoCompleteTextView
    private lateinit var medicion : EditText
    private lateinit var guardar : Button
    private lateinit var prefs : SharedPreferences
    private val llave = "LaLlave"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signos_vitales)

//Array adapter de enfermedades crónicas.-----------------------------------------------------------
        val vitales = resources.getStringArray(R.array.signosVitales)
        val arrayAdapterSignosVitales = ArrayAdapter(this, R.layout.dropdown_medible, vitales)
        arregloSignos = findViewById(R.id.seleccionVitales)
        arregloSignos.setAdapter(arrayAdapterSignosVitales)
//--------------------------------------------------------------------------------------------------
        medicion = findViewById(R.id.medicion_Vitales)
        guardar = findViewById(R.id.guardar_resultados_Vitales)

        prefs = PreferenceManager.getDefaultSharedPreferences(this)
        if(prefs.getInt(llave, -1)==-1){
            startActivity(Intent(this, LoginActivity::class.java))
        }else{
            val gson = Gson()
            val Pacientes = ArrayList<Paciente>()

 //Obtiene los datos almacenados ---------------------------------------------------------------
            try {
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
            }catch (e: Exception){
                Log.d("Error", e.toString())
                Toast.makeText(applicationContext, "Error al cargar", Toast.LENGTH_LONG).show()
            }
  //------------------------------------------------------------------------------------------------

//Almacena los datos al momento de guardar ---------------------------------------------------------
            guardar.setOnClickListener(){
                var encontrado = false
                val signoMedido = arregloSignos.getText().toString()
                val resultadoMedido = medicion.getText().toString().toFloat()
                var arraysito = ArrayList<Float>()
                arraysito.add(resultadoMedido)
                var medicionActual = SignoVital(signoMedido, arraysito)

                var SignosVitales = ArrayList<SignoVital>()

                if (Pacientes[prefs.getInt(llave,-1)].vitales == null){
                    SignosVitales.add(medicionActual)
                }
                else{
                    var conta = 0
                    var encontrado = false
                    SignosVitales = Pacientes[prefs.getInt(llave,-1)].vitales!!
                    for (n: SignoVital in SignosVitales){
                        if(n.Tipo == signoMedido){
                            SignosVitales[conta].medidas!!.add(resultadoMedido)
                            encontrado = true
                        }
                        conta++
                    }
                    if(!encontrado){
                        SignosVitales.add(medicionActual)
                    }
                }
                Pacientes[prefs.getInt(llave,-1)].vitales = SignosVitales
                var conta2 =0

                for (n:SignoVital in SignosVitales){
                    Log.d("Tipo Paciente", Pacientes[prefs.getInt(llave,-1)].vitales!![conta2].Tipo)
                    var json = gson.toJson(Pacientes[prefs.getInt(llave,-1)].vitales!![conta2])
                    Log.d("", json)
                    conta2++
                }
                //Falta guardar en archivo

            }
//--------------------------------------------------------------------------------------------------
        }

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
}