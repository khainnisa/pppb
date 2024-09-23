package com.example.modul6


import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment
import com.example.modul6.databinding.ActivityMainBinding
import java.text.DateFormat
import java.util.Calendar

// minimal kaya gini sebelum mulai project
class MainActivity : AppCompatActivity() {
    DatePickerDialog.OnDateSetListener
    private lateinit var binding: ActivityMainBinding
    private lateinit var provinces: Array<String>
    private val countries = arrayOf(
    "Indonesia",
    "United States",
    "United Kingdom",
    "Germany",
    "France",
    "Australia",
    "Japan",
    "China",
    "Brazil",
    "Canada"
    )

//    btnShowAlertD ialog.setOnClickListener
//    {
//        val dialog = AlertDialog.Builder(this@MainActivity)
//        dialog.setTitle("Keluar")
//        dialog.setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
//        dialog.setPositiveButton("Ya") { dialog, which ->
//            //lakukan sesuatu ketika tombol positif diklik
//            finish()
//        }
//        dialog.setNegativeButton( "Tidak") { dialog, _ ->
//            //lakukan sesuatu ketika tombol negatif diklik
//            dialog.dismiss()
//        }
//        // Membuat dan menampilkan dialog
//        val dialogConfirm = dialog.create()
//        dialogConfirm.show()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        cara manggil array
        provinces = resources.getStringArray(R.array.provinces)

        with (binding) {
            //            ngeset tampilan pertitem, udah otomatis ada toolbarnya
            val adapterProvince = ArrayAdapter(
                this@MainActivity, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                provinces)

            val adapterCountry = ArrayAdapter(
                this@MainActivity,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                countries)

            spinnerProvince.adapter = adapterProvince
            spinnerCountry.adapter = adapterCountry

//            supaya bisa ngeklik pilihan, harus masukin kalo buat spinner
//            supaya ketika select masuk ke dalam variabel onItemSelectedListener
            spinnerCountry.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(
                        p0: AdapterView<*>?,
                        p1: View?,
                        position: Int,
                        p3: Long) {
                        Toast.makeText(
                            this@MainActivity,
                            "Selected Country: " + countries.get(position),
//                            length.long itu durasi munculnya lebih lama
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }

            datePicker.init(
                datePicker.year,
                datePicker.month,
                datePicker.dayOfMonth
//                month dimulai dari nol
            ) {_,year, month, day ->
                Toast.makeText(this@MainActivity, "$year/${month+1}/$day)", Toast.LENGTH_LONG).show()
            }

            timePicker.setOnTimeChangedListener { _, hour, minute ->
                val selectedTime = String.format("%02d:%02d", hour, minute)
                Toast.makeText(
                    this@MainActivity,
                    selectedTime,
                    Toast.LENGTH_LONG
                ).show()
            }

            btnDateDialog.setOnClickListener {
                val datePicker
            }

            btnTimeDialog.setOnClickListener
        }
    }
}

class DatePicker: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val monthOfYear = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(
            requireActivity(),
            activity as DatePickerDialog.OnDateSetListener,
            year,
            monthOfYear,
            dayOfMonth
        )
    }
}

class TimePicker: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        return TimePickerDialog(
            requireContext(),
            activity as TimePickerDialog.OnTimeSetListener,
            hourOfDay,
            minute,
            android.text.format.DateFormat.is24HourFormat(activity)
        )
    }
}

class DialogExit : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val binding = DialogExitBinding.inflate(inflater)
        with (binding){
            btnYes.setOnClickListener {
                requireActivity().finish()
            }
            btnNo.setOnClickListener {
                dismiss()
            }
        }
        builder.setView(binding.root)
        return builder.create()
    }
}