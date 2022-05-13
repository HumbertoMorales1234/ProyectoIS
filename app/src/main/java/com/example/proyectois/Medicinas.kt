package com.example.proyectois

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class Medicinas : AppCompatActivity() {
    lateinit var botonNoti: Button
    //Creación del canal de manera global

    val canalID = "chat"
    val canalNombre = "chat"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicinas)

        //Botón de prueba

        botonNoti = findViewById(R.id.botonNoti)


        //Verificar si la versión requiere canal, a partir de la versión "O (Oreo)" sí se requiere un canal

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val importancia = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(canalID,canalNombre,importancia)

        //Se manda al manager de aplicaciones
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)

        //Configuración de la notificación
        botonNoti.setOnClickListener{
            //Configuración de la notificación
            val notificacion = NotificationCompat.Builder(this, canalID).also { noti->
            noti.setContentTitle("Titulo de prueba")
            noti.setContentText("Texto de prueba")
            noti.setSmallIcon(R.mipmap.logo_e_salud)

            }.build()

            val notificationManager = NotificationManagerCompat.from(applicationContext)
            notificationManager.notify(1,notificacion)

            }
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
            R.id.signosVitales -> startActivity(Intent(this, Resumen::class.java))
            R.id.resumen -> startActivity(Intent(this, Resumen::class.java))
            R.id.perfilUsuario -> startActivity(Intent(this, EdicionPerfil::class.java))
            R.id.medicinas -> startActivity(Intent(this, Medicinas::class.java))
            R.id.salirSesion -> startActivity(Intent(this, LoginActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
//--------------------------------------------------------------------------------------------------

        //Construcción de la notificación




    }
