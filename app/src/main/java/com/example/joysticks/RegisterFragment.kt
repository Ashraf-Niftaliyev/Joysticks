package com.example.joysticks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.esrefnifteliyev.joysticks.R
import com.esrefnifteliyev.joysticks.databinding.FragmentRegisterBinding
import com.example.joysticks.model.UserModel
import com.example.joysticks.roomdb.AppRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.HaveAccount.setOnClickListener {view ->
            Navigation.findNavController(view).navigate(R.id.loginFragment)
        }

        binding.registerButton.setOnClickListener {
            var username = binding.RegisterUserName.text.toString()
            var email = binding.RegEmailEdittext.text.toString()
            var password = binding.RegPasswordEditText.text.toString()

            var userModel = UserModel(
                0,
                username,
                email,
                password
            )

            var repo = AppRepo(requireContext())
            CoroutineScope(Dispatchers.Main).launch {
                repo.insertUser(userModel)
            }
             Navigation.findNavController(it).navigate(R.id.loginFragment)
        }
    }

}