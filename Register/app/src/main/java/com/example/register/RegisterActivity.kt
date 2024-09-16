package com.example.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import com.example.register.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    // Inisiasi binding
    private val binding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    // Variabel untuk menyimpan data input user
    var username: String = ""
    var email: String = ""
    var phone: String = ""
    var pass: String = ""
    var isChecked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Memastikan tampilan menyesuaikan dengan status bar
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Mengambil data input user dan memasukkannya ke variabel
        with(binding) {
            registerEdittextUsn.doAfterTextChanged {
                username = it.toString()
            }
            registerEdittextEmail.doAfterTextChanged {
                email = it.toString()
            }
            registerEdittextPhone.doAfterTextChanged {
                phone = it.toString()
            }
            registerEdittextPass.doAfterTextChanged {
                pass = it.toString()
            }

            registerCheckbox.setOnCheckedChangeListener { _, isChecked ->
                this@RegisterActivity.isChecked = isChecked
            }

            // Menambahkan listener untuk tombol register
            buttonRegister.setOnClickListener {
                if (validation()) {
                    kirimData()
                } else {
                    // Menampilkan pesan toast untuk memberitahu pengguna jika validasi gagal
                    Toast.makeText(this@RegisterActivity, "Mohon isi data dengan benar", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Pindah ke LoginActivity setelah registrasi berhasil
    private val launcherToLoginActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            // Bisa ditambahkan logika di sini jika diperlukan
        }

    // Mengirim data ke LoginActivity
    private fun kirimData() {
        val intentToLogin = Intent(this, LoginActivity::class.java).apply {
            putExtra(key_usn, username)
            putExtra(key_pass, pass)
            putExtra(key_email, email)
            putExtra(key_phone, phone)
        }

        // Meluncurkan LoginActivity
        launcherToLoginActivity.launch(intentToLogin)
    }

    // Validasi input user
    private fun validation(): Boolean {
        if (username.isBlank()) {
            Toast.makeText(this, "Username tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return false
        }
        if (email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Email tidak valid", Toast.LENGTH_SHORT).show()
            return false
        }
        if (phone.isBlank() || phone.length < 10) {
            Toast.makeText(this, "Nomor telepon harus terdiri dari minimal 10 angka", Toast.LENGTH_SHORT).show()
            return false
        }
        if (pass.isBlank() || pass.length < 6) {
            Toast.makeText(this, "Password harus minimal 6 karakter", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!isChecked) {
            Toast.makeText(this, "Anda harus menyetujui persyaratan", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    companion object {
        const val key_usn = "usn"
        const val key_pass = "pass"
        const val key_email = "email"
        const val key_phone = "phone"
    }
}
