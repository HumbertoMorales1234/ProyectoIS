package com.example.proyectois

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.proyectois.clases.AlarmReceiver
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class AgregarMedicina : AppCompatActivity() {
    lateinit var btnHora : Button
    lateinit var horaSeleccionada : TextView
    var hora : Int =0
    var minuto : Int=0
    private lateinit var picker : MaterialTimePicker
    private lateinit var calendar : Calendar
    private lateinit var alarmManager: AlarmManager
    private lateinit var  pendingIntent: PendingIntent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregarmedicina)

        horaSeleccionada = findViewById(R.id.lblAddMedTimeSelected)

        btnHora = findViewById(R.id.btnSeleccionarHora)
        btnHora.setOnClickListener {
            showTimePicker()
            horaSeleccionada.text= "Hora seleccionada: "+String.format("%02d",hora)+":"+String.format("%02d",minuto)
        }
    }





    //Funciones para la alarma--------------------------------------------------------------------------
    private fun cancelAlarm() {
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this,0,intent,0)
        alarmManager.cancel(pendingIntent)
        Toast.makeText(this,"Alarma cancelada", Toast.LENGTH_SHORT).show()
    }

    private fun setAlarm() {
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        intent.putExtra("hora",hora.toLong())
        intent.putExtra("minuto",minuto.toLong())
        pendingIntent = PendingIntent.getBroadcast(this,(0..2147483647).random(),intent,0)
        alarmManager.set(

            AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pendingIntent
        )
        Toast.makeText(this,"Alarma puesta a las:"+String.format("%02d",hora)+":"+String.format("%02d",minuto), Toast.LENGTH_SHORT).show()

    }

    private fun showTimePicker() {
        picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Selecciona la hora")
            .build()

        picker.show(supportFragmentManager,"e-salud")
        picker.addOnPositiveButtonClickListener {

            hora=picker.hour
            minuto=picker.minute



            calendar = Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY]=hora
            calendar[Calendar.MINUTE]=minuto
            calendar[Calendar.SECOND]=0
            calendar[Calendar.MILLISECOND]=0

        }

    }

    private fun createNotificationChannel() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name : CharSequence = "e-saludReminderChannel"
            val descripcion = "Canal para alarma"
            val importancia = NotificationManager.IMPORTANCE_HIGH
            val canal = NotificationChannel("e-salud",name,importancia)
            canal.description = descripcion
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(canal)
        }

    }

//--------------------------------------------------------------------------------------------------
}