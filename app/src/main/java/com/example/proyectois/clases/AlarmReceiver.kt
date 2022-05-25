package com.example.proyectois.clases

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.proyectois.Medicinas
import com.example.proyectois.R

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val i = Intent(context,Medicinas::class.java)
        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val nombreMed= intent.getStringExtra("nombreMed")
        val nombreTrat= intent.getStringExtra("nombreTrat")
        val pendingIntent = PendingIntent.getActivity(context,0,i,0)
        //Se construye la notificación
        val builder = NotificationCompat.Builder(context!!,"e-salud")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("E-Salud: Medicinas pendientes")
            .setContentText("Debes tomar la medicina: "+nombreMed+" del tratamiento: "+nombreTrat)
            .setAutoCancel(false)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        // Muestra la notificación
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(123,builder.build())

    }
}