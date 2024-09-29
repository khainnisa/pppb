package com.example.modul6

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.modul6.databinding.ActivityMainBinding
import com.example.modul6.databinding.FragmentTimePickerBinding
import java.util.Calendar

// TimePicker class yang merupakan turunan dari DialogFragment
class TimePicker : DialogFragment() {

    // Lazy initialization untuk binding layout FragmentTimePickerBinding
    private val binding by lazy {
        FragmentTimePickerBinding.inflate(layoutInflater)
    }

    // onCreateDialog digunakan untuk membuat dan mengatur dialog TimePicker
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Mengambil waktu saat ini (jam dan menit) dari Calendar
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY) // Mendapatkan jam saat ini
        val minute = calendar.get(Calendar.MINUTE) // Mendapatkan menit saat ini

        // Membuat dan mengembalikan instance dari TimePickerDialog
        return TimePickerDialog(
            requireActivity(), // Activity tempat dialog ini ditampilkan
            activity as TimePickerDialog.OnTimeSetListener, // Listener yang akan menerima input waktu dari pengguna
            hour, // Jam saat ini yang ditampilkan pertama kali di TimePicker
            minute, // Menit saat ini yang ditampilkan pertama kali di TimePicker
            true // Parameter untuk menampilkan format waktu 24 jam (true) atau AM/PM (false)
        )
    }
}
