package com.example.tugas12

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class NotifReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        when (action) {
            "ACTION_SUKA" -> {
                // Tambah counter "Ganteng"
                CounterManager.incrementGanteng()
                Log.d("NotifReceiver", "Ganteng Counter: ${CounterManager.getGanteng()}")
            }
            "ACTION_TIDAK_SUKA" -> {
                // Tambah counter "Jelek"
                CounterManager.incrementJelek()
                Log.d("NotifReceiver", "Jelek Counter: ${CounterManager.getJelek()}")
            }
            else -> return
        }

        // Update notifikasi jika dibutuhkan
        val updateIntent = Intent("com.example.tugas12.UPDATE_COUNTER")
        context?.sendBroadcast(updateIntent)
        Log.d("NotifReceiver", "Broadcast UPDATE_COUNTER dikirim")
    }
}
