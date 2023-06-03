package com.example.joysticks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.esrefnifteliyev.joysticks.R
import com.esrefnifteliyev.joysticks.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.WelcomeLoginButton.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }

        binding.WelcomeDontHaveAccount.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }
    }

}