package com.example.ppapb_p6

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.Calendar

// DatePicker class yang merupakan turunan dari DialogFragment
class DatePicker : DialogFragment() {

    // onCreateDialog digunakan untuk membuat dan mengatur dialog DatePicker
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Mengambil tanggal saat ini (tahun, bulan, dan hari) dari Calendar
        val calendar = Calendar.getInstance() // Membuat instance Calendar untuk mendapatkan waktu saat ini
        val year = calendar.get(Calendar.YEAR) // Mendapatkan tahun saat ini
        val monthOfYear = calendar.get(Calendar.MONTH) // Mendapatkan bulan saat ini (0-11)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH) // Mendapatkan hari dalam bulan saat ini

        // Membuat dan mengembalikan instance dari DatePickerDialog
        return DatePickerDialog(
            requireActivity(), // Activity tempat dialog ini ditampilkan
            activity as DatePickerDialog.OnDateSetListener, // Listener yang akan menerima input tanggal dari pengguna
            year, // Tahun saat ini yang ditampilkan pertama kali di DatePicker
            monthOfYear, // Bulan saat ini yang ditampilkan pertama kali di DatePicker
            dayOfMonth // Hari dalam bulan yang ditampilkan pertama kali di DatePicker
        )
    }
}
