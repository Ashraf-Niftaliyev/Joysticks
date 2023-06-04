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
import com.esrefnifteliyev.joysticks.databinding.FragmentLoginBinding
import com.example.joysticks.roomdb.AppRepo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.DontHaveAccount.setOnClickListener {view ->
            Navigation.findNavController(view).navigate(R.id.registerFragment)
        }
         val auth = Firebase.auth
        binding.loginButton.setOnClickListener {
            val email = binding.loginEmailEdittext.text.toString()
            val password = binding.loginPasswordEditText.text.toString()
                auth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG)
                            .show()
                        findNavController().navigate(R.id.MFragment)
                    }
                    .addOnFailureListener {
                        Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_LONG)
                            .show()
                    }


            val repo = AppRepo(requireContext())
            CoroutineScope(Dispatchers.Main).launch {
                var list = repo.getAllUsersEmail(email,password)

                if (list.isNotEmpty()){
                    Navigation.findNavController(it).navigate(R.id.MFragment)
                }
            }

        }
    }
}