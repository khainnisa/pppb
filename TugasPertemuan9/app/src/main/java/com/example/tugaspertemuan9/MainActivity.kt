package com.example.tugaspertemuan9

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tugaspertemuan9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var profileName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            val navController = findNavController(R.id.nav_host_fragment)
            bottomNavigation.setupWithNavController(navController)
        }

        // Method to receive data from BerandaFragment
        fun passDataToProfileFragment(name: String) {
            profileName = name
        }

        // Method to get data for ProfileFragment
        fun getProfileName(): String? {
            return profileName
        }
    }

}
