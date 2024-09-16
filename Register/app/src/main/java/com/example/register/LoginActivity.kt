package com.example.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import com.example.register.RegisterActivity.Companion.key_email
import com.example.register.RegisterActivity.Companion.key_pass
import com.example.register.RegisterActivity.Companion.key_phone
import com.example.register.RegisterActivity.Companion.key_usn
import com.example.register.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    var username: String = ""
    var pass: String = ""
    var isChecked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Mengambil data username dan password yang didaftarkan dari RegisterActivity
        val regisUsername = intent.getStringExtra(key_usn)
        val regisPass = intent.getStringExtra(key_pass)

        // Memastikan data intent diterima (untuk debugging)
        Toast.makeText(
            this,
            "Registered Username: $regisUsername",
            Toast.LENGTH_SHORT
        ).show()

        // Mengambil input dari user untuk login
        with(binding) {
            loginEdittextUsn.doAfterTextChanged {
                username = it.toString()
            }
            loginEdittextPass.doAfterTextChanged {
                pass = it.toString()
            }
            loginCheckbox.setOnCheckedChangeListener { _, isChecked ->
                this@LoginActivity.isChecked = isChecked
            }

            // Saat tombol login diklik, lakukan validasi
            loginButton.setOnClickListener {
                if (validation(username, pass, regisUsername, regisPass)) {
                    kirimData() // Kirim data ke MainActivity jika validasi berhasil
                } else {
                    Toast.makeText(this@LoginActivity, "Username atau password salah", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Mengarahkan pengguna ke halaman MainActivity jika login berhasil
    private fun kirimData() {
        val intentToMain = Intent(this, MainActivity::class.java).apply {
            putExtra(key_usn, username)  // Mengirim username ke MainActivity (jika perlu)
        }
        startActivity(intentToMain)
        finish()  // Menutup LoginActivity setelah berhasil login
    }

    // Fungsi validasi username dan password
    private fun validation(
        inputUsername: String,
        inputPassword: String,
        registeredUsername: String?,
        registeredPassword: String?
    ): Boolean {
        // Memastikan username dan password yang dimasukkan sesuai dengan yang didaftarkan
        return inputUsername == registeredUsername && inputPassword == registeredPassword
    }
}
