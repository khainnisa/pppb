package com.example.tugaspertemuan11retrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Inisialisasi View
        val imgProfile: ImageView = findViewById(R.id.img_profile)
        val txtName: TextView = findViewById(R.id.txt_name)
        val txtEmail: TextView = findViewById(R.id.txt_email)

        // Ambil data dari intent
        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val avatar = intent.getStringExtra("avatar")

        // Set data ke tampilan
        txtName.text = "Name: $name"
        txtEmail.text = "Email: $email"
        Picasso.get().load(avatar).centerCrop().fit().into(imgProfile)
    }
}
