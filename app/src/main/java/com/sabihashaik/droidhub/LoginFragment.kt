package com.sabihashaik.droidhub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sabihashaik.droidhub.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater,container,false)
        auth = Firebase.auth

        binding.orSignupButton.setOnClickListener{view:View ->
            view.findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        binding.orLoginButton.setOnClickListener{
            val currentUser = auth.currentUser
            var email = binding.loginEmailField.text.toString()
            var password = binding.loginPasswordField.text.toString()

            if(currentUser==null) {
                val filesIntent = Intent(context, FilesActivity::class.java)
                startActivity(filesIntent)
            }
            else{
                signInToApp(email,password)
            }

        }
        return binding.root
    }

    private fun signInToApp(email:String, password:String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val filesIntent = Intent(context, FilesActivity::class.java)
                    startActivity(filesIntent)
                } else {
                     Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }

            }
    }

}