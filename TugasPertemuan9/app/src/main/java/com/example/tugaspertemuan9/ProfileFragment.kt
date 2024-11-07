package com.example.tugaspertemuan9

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.tugaspertemuan9.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // Mengambil argumen "nameText" yang dikirim dari BerandaFragment melalui navigation component
//        val nameText = arguments?.getString("nameText") ?: ""
//        binding.tvUsername.text = nameText // Pastikan `tvProfileName` ada di layout XML `fragment_profile.xml`
//    }

//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        with(binding) {
//            val args: ProfileFragmentArgs by navArgs()
//            tvUsername.setText(args.username)
//        }
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ambil username dari SharedPreferences
        val sharedPref = requireActivity().getSharedPreferences("MyPrefs", AppCompatActivity.MODE_PRIVATE)
        val username = sharedPref.getString("username", "")

        // Tampilkan username di ProfileFragment (misalnya pada TextView tvProfileName)
        binding.tvUsername.text = username
    }

            override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
