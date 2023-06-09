package com.example.joysticks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.esrefnifteliyev.joysticks.R
import com.esrefnifteliyev.joysticks.databinding.FragmentRegisterBinding
import com.example.joysticks.model.UserModel
import com.example.joysticks.roomdb.AppRepo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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
        val auth = Firebase.auth
        binding.registerButton.setOnClickListener {
            var username = binding.RegisterUserName.text.toString()
            val email = binding.RegEmailEdittext.text.toString()
            val password = binding.RegPasswordEditText.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        findNavController().navigate(R.id.loginFragment)
                        Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG)
                            .show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_LONG)
                            .show()
                    }
            }


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