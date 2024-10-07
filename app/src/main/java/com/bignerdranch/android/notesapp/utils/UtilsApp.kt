package com.bignerdranch.android.notesapp.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bignerdranch.android.notesapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UtilsApp {
    companion object {

        /**
         * Создаём канал для Push уведомлений
         */
        fun createNotificationChannel(context: Context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channelId = "my_channel"
                val channelName = "My Channel"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val channel = NotificationChannel(channelId, channelName, importance).apply {
                    description = "Канал уведомлений"
                }

                val notificationManager: NotificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }

        /**
         * Создаём Push уведомление
         */
        fun sendPushInfo(fragment: Fragment, textPushHeader: String, textPushBody: String) {
            val context = fragment.requireContext()

            //Проверка на разрешение отправлять уведомления
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                fragment.requestPermissions(
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    1
                )
                return
            }

            val builder = NotificationCompat.Builder(context, "my_channel")
                .setSmallIcon(R.drawable.notes_icon_btn)
                .setContentTitle(textPushHeader)
                .setContentText(textPushBody)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)

            with(NotificationManagerCompat.from(context)) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                notify(1, builder.build())
            }
        }

        /**
         * Метод для форматирования Data в нужный визуальный формат
         */
        @SuppressLint("SimpleDateFormat")
        fun formatDate(date: Date): String {
            val dateFormat = SimpleDateFormat("d MMMM yyyy '|' 'время' HH:mm:ss", Locale("ru"))
            return dateFormat.format(date)
        }

        /**
         * Метод для скрытия\отображения нижнего бара BottomNavigationView
         */
        fun bottomNavVisibility(bottomNavView: BottomNavigationView?, visibility: Int) {
            bottomNavView?.visibility = visibility
        }
    }
}