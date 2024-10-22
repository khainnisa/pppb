package com.example.tugaspertemuan8

import android.graphics.Color
import com.example.tugaspertemuan8.R
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tugaspertemuan8.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val tabNama = arrayOf("Register", "Login")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            viewPager.adapter = SectionPageAdapter(this@MainActivity)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = tabNama[position]
            }.attach()  // Pindahkan attach di luar blok
        }

        // Set judul ActionBar dengan warna khusus
        val title = SpannableString("MyTabLayout")
        title.setSpan(
            ForegroundColorSpan(Color.WHITE),  // Atur warna ke magenta (atau warna lain)
            0, title.length,  // Rentang seluruh teks
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Terapkan judul berwarna ke ActionBar
        supportActionBar?.title = title
    }

    //    menu udh ada tapi belum ada action
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(/* menuRes = */ R.menu.menu_options, /* menu = */ menu)
        for (i in 0 until menu.size()) {
            val item = menu.getItem(i)
            item.icon?.setTint(resources.getColor(android.R.color.white))  // Ubah warna ikon
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_logout -> {
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    // Method untuk menerima data dari LoginFragment
    fun receiveData(data: String) {
        // Lakukan sesuatu dengan data, misalnya log untuk melihat data yang diterima
        Log.d("MainActivity", "Data received: $data")
    }

}