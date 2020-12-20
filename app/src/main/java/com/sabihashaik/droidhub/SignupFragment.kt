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
import com.sabihashaik.droidhub.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {
   lateinit var binding: FragmentSignupBinding
   private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container,false)
        //this.context?.let { FirebaseApp.initializeApp(it) };
        auth = Firebase.auth

        binding.LoginButton.setOnClickListener{view:View->
            view.findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }

        binding.SignUpButton.setOnClickListener{
            var email = binding.emailInputField.text.toString()
            var password =  binding.passwordInputField.text.toString()

            createNewUser(email,password)

        }
        return binding.root

      }

    private fun createNewUser(email:String,password:String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this.context, "new User Created", Toast.LENGTH_SHORT).show()
                    startFileIntent()

                 } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this.context, "Failed "+task.exception, Toast.LENGTH_SHORT).show()
           }


            }

    }

    private fun startFileIntent() {
        val filesIntent = Intent(context,FilesActivity::class.java)
        startActivity(filesIntent)
    }


}
