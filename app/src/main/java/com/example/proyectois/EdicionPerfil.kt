package com.example.proyectois

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView


class EdicionPerfil : AppCompatActivity() {

    private lateinit var arregloSangres : AutoCompleteTextView
    private lateinit var arregloCronos : AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edicion_perfil)

        // Configuracion del array adapter de tipos sanguineos - Esto es para que haga funcion de cajita
        val sangres = resources.getStringArray(R.array.tiposSanguineos)
        val arrayAdapterSangre = ArrayAdapter(this, R.layout.dropdown_tipos_sangre, sangres)
        arregloSangres = findViewById(R.id.seleccionSanguineoeditaP)
        arregloSangres.setAdapter(arrayAdapterSangre)

        //Array adapter de enfermedades crónicas.
        val cronicos = resources.getStringArray(R.array.padecimientosCronicos)
        val arrayAdapterCronicos = ArrayAdapter(this, R.layout.dropdown_tipos_cronicos, cronicos)
        arregloCronos = findViewById(R.id.seleccionCronicoeditaP)
        arregloCronos.setAdapter(arrayAdapterCronicos)
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
            R.id.signosVitales -> startActivity(Intent(this, Resumen::class.java))
            R.id.resumen -> startActivity(Intent(this, Resumen::class.java))
            R.id.perfilUsuario -> startActivity(Intent(this, EdicionPerfil::class.java))
            R.id.medicinas -> startActivity(Intent(this, Medicinas::class.java))
            R.id.salirSesion -> startActivity(Intent(this, LoginActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
//--------------------------------------------------------------------------------------------------
}