package com.example.tugaspertemuan12broadcastreceivernotification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class NotifReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val action = intent?.action
        when (action) {
            "ACTION_LIKE" -> {
                Toast.makeText(context, "Anda menyukai!", Toast.LENGTH_SHORT).show()
                updateLikeCount(context)

                // Kirim broadcast lokal
                val updateIntent = Intent("UPDATE_COUNTERS")
                updateIntent.putExtra("action", "like")
                LocalBroadcastManager.getInstance(context).sendBroadcast(updateIntent)
            }
            "ACTION_DISLIKE" -> {
                Toast.makeText(context, "Anda tidak menyukai!", Toast.LENGTH_SHORT).show()
                updateDislikeCount(context)

                // Kirim broadcast lokal
                val updateIntent = Intent("UPDATE_COUNTERS")
                updateIntent.putExtra("action", "dislike")
                LocalBroadcastManager.getInstance(context).sendBroadcast(updateIntent)
            }
        }
    }

    private fun updateLikeCount(context: Context) {
        val sharedPref = context.getSharedPreferences("counter_prefs", Context.MODE_PRIVATE)
        val currentCount = sharedPref.getInt("like_count", 0)
        sharedPref.edit().putInt("like_count", currentCount + 1).apply()
    }

    private fun updateDislikeCount(context: Context) {
        val sharedPref = context.getSharedPreferences("counter_prefs", Context.MODE_PRIVATE)
        val currentCount = sharedPref.getInt("dislike_count", 0)
        sharedPref.edit().putInt("dislike_count", currentCount + 1).apply()
    }
}
