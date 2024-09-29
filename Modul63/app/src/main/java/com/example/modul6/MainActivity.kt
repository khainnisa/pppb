package com.example.modul6

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.modul6.databinding.ActivityMainBinding
import java.util.Calendar
import java.util.Locale

//link gdrive video: https://drive.google.com/file/d/1uwq8zgdqyvn5cE-NvITKo41sLL33SAIL/view?usp=drivesdk

class MainActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    var tujuanKereta = ""
    var selectedDate = ""

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val hargaTiketMap = mapOf(
        "Jakarta" to 300000,
        "Bandung" to 250000,
        "Surabaya" to 400000,
        "Yogyakarta" to 350000,
        "Semarang" to 275000
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Setup TimePicker
        setupTimePickerButton()

        // Setup Spinner untuk tujuan kereta
        setupTujuanSpinner()

        // Setup DatePicker
        setupDatePickerButton()

        // Setup Register Button untuk menampilkan AlertDialog
        binding.registerButton.setOnClickListener {
            if (validateInputs()) {
                // Mendapatkan tujuan yang dipilih
                val selectedCity = binding.spinnerCity.selectedItem.toString()

                // Mendapatkan harga berdasarkan tujuan
                val hargaTiket = hargaTiketMap[selectedCity]

                // Menampilkan custom alert dengan harga tiket
                showCustomAlert()
            } else {
                Toast.makeText(this, "Mohon lengkapi semua data!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    // Fungsi untuk setup DatePicker
    private fun setupDatePickerButton() {
        val calendar = Calendar.getInstance()

        // Listener ketika tombol untuk tanggal keberangkatan ditekan
        binding.mainTglButton.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, monthOfYear, dayOfMonth ->
                    // Format tanggal yang dipilih
                    selectedDate = String.format(Locale.getDefault(), "%02d/%02d/%d", dayOfMonth, monthOfYear + 1, year)

                    // Set tanggal yang dipilih sebagai teks di tombol
                    binding.mainTglButton.text = selectedDate

                    // Tampilkan toast dengan tanggal yang dipilih (opsional)
                    Toast.makeText(this@MainActivity, "Selected Date: $selectedDate", Toast.LENGTH_SHORT).show()
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            // Membatasi tanggal DatePicker hanya untuk hari ini dan seterusnya
            datePickerDialog.datePicker.minDate = calendar.timeInMillis

            // Tampilkan dialog DatePicker
            datePickerDialog.show()
        }
    }

    // Setup Spinner untuk tujuan kereta
    private fun setupTujuanSpinner() {
        val tujuanArray = resources.getStringArray(R.array.kereta)
        val adapter = ArrayAdapter(this@MainActivity,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            tujuanArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCity.adapter = adapter

        // Set visibility spinner ke GONE pada awalnya
        binding.spinnerCity.visibility = View.GONE

        // Toggle visibilitas Spinner ketika tombol Pilih Tujuan ditekan
        binding.btnChooseCity.setOnClickListener {
            if (binding.spinnerCity.visibility == View.GONE) {
                binding.spinnerCity.visibility = View.VISIBLE
            } else {
                binding.spinnerCity.visibility = View.GONE
            }
        }

        binding.spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Set tujuan yang dipilih
                tujuanKereta = parent.getItemAtPosition(position).toString()
                val hargaTiket = hargaTiketMap[tujuanKereta]
                // Tampilkan pilihan tujuan di tombol dan sembunyikan Spinner lagi
                binding.btnChooseCity.text = tujuanKereta
                binding.spinnerCity.visibility = View.GONE
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Tidak ada tindakan
            }
        }
    }

    // Fungsi untuk setup TimePicker
    private fun setupTimePickerButton() {
        binding.mainJamButton.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                this,
                this, // Implementasi listener OnTimeSetListener
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                Calendar.getInstance().get(Calendar.MINUTE),
                true // 24-hour format
            )
            timePickerDialog.show()
        }
    }

    // Fungsi untuk menampilkan Custom Alert Dialog
    private fun showCustomAlert() {
        // Inflate custom layout untuk dialog
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.custom_alert, null)

        // Membuat AlertDialog Builder
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setView(view)

        // Menangani interaksi tombol di custom layout
        val cancelButton = view.findViewById<Button>(R.id.alertCancelButton)
        val buyButton = view.findViewById<Button>(R.id.alertBuyButton)
        val priceTextView = view.findViewById<TextView>(R.id.alertPrice)

        // Mendapatkan tujuan yang dipilih
        val selectedCity = binding.spinnerCity.selectedItem.toString()

        // Mendapatkan harga berdasarkan tujuan
        val hargaTiket = hargaTiketMap[selectedCity]

        // Menampilkan harga yang sesuai di alert
        priceTextView.text = "Rp$hargaTiket"

        // Membuat AlertDialog dan menampilkannya
        val alertDialog = alertDialogBuilder.create()

        // Handle tombol Batal
        cancelButton.setOnClickListener {
            alertDialog.dismiss() // Tutup dialog saat Batal ditekan
        }

        // Handle tombol Beli
        buyButton.setOnClickListener {
            // Ambil data yang diisi
            val tujuan = tujuanKereta
            val tanggal = selectedDate
            val waktu = binding.mainJamButton.text.toString()
            val nama = binding.mainNama.text.toString() // Ambil nama dari EditText

            // Intent ke SecondActivity dan mengirimkan data
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            intent.putExtra("TUJUAN", tujuan)
            intent.putExtra("TANGGAL", tanggal)
            intent.putExtra("WAKTU", waktu)
            intent.putExtra("NAMA", nama) // Kirim nama

            startActivity(intent) // Pindah ke SecondActivity

            alertDialog.dismiss() // Tutup dialog
        }

        alertDialog.show() // Menampilkan dialog
    }


    // Callback ketika waktu dipilih di TimePicker
    override fun onTimeSet(view: android.widget.TimePicker?, hourOfDay: Int, minute: Int) {
        val selectedTime = String.format("Pukul %02d:%02d", hourOfDay, minute)
        Log.d("TimePicker", selectedTime)
        binding.mainJamButton.text = selectedTime
    }

    // Callback ketika tanggal dipilih di DatePicker (tidak terpakai karena sudah langsung diatur dalam setupDatePickerButton)
    override fun onDateSet(view: android.widget.DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val selectedDate = "$dayOfMonth/${month + 1}/$year"
        Toast.makeText(this@MainActivity, selectedDate, Toast.LENGTH_SHORT).show()
    }

    // Fungsi untuk validasi input
    private fun validateInputs(): Boolean {
        val nama = binding.mainNama.text.toString()
        val waktu = binding.mainJamButton.text.toString()
        return nama.isNotEmpty() && tujuanKereta.isNotEmpty() && selectedDate.isNotEmpty() && waktu.isNotEmpty()
    }
}
