package com.example.ijimjk

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.ijimjk.databinding.ActivityMainBinding
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// kelas utama dari aplikasi, turunan dari AppCompatActivity
class MainActivity : AppCompatActivity() {

//    deklarasi variabel binding untuk akses elemen UI yang dihubungkan dengan layout menggunakan View Binding
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
//        method dipanggil ketika MainActivity pertama kali dibuat, digunakan utk inisiasi UI
        super.onCreate(savedInstanceState)
//        inisiasi binding dengan inflasi layout dan menerapkan tampilan konten activity dari binding root
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        mengatur listener untuk tombol login di layout. ketika didklik, tindakan dalam blok dieksekusi
        with(binding) {
            tombolLogin.setOnClickListener {
//                mengambil nilai input dari EditText untuk username dan password kemudian mengubah menjadi string
                val edtUsername = findViewById<EditText>(R.id.txt_username)
                val edtPassword = findViewById<EditText>(R.id.txt_password)
                val username = edtUsername.text.toString()
                val password = edtPassword.text.toString()
//                menampilkan pesan toast yang menunjukkan username dan passowrd yang telah diinput oleh pengguna
                Toast.makeText(this@MainActivity, "Username : $username \nPassowrd : $password", Toast.LENGTH_LONG).show()
            }
        }
    }
}