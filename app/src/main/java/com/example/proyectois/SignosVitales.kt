package com.example.proyectois

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class SignosVitales : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signos_vitales)
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