package com.example.joysticks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esrefnifteliyev.joysticks.databinding.FragmentMBinding

class MFragment : Fragment() {
    private lateinit var binding: FragmentMBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMBinding.inflate(inflater,container,false)
        return binding.root
    }


}