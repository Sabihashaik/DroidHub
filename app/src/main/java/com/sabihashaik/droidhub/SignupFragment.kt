package com.sabihashaik.droidhub

import android.content.Context
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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sabihashaik.droidhub.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {
   lateinit var binding: FragmentSignupBinding
   private lateinit var auth: FirebaseAuth
   private lateinit var db: FirebaseFirestore
   private lateinit var user:HashMap<String,String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater, container,false)
        //this.context?.let { FirebaseApp.initializeApp(it) };
        auth = Firebase.auth
        db = Firebase.firestore


        binding.LoginButton.setOnClickListener{view:View->
            view.findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }

        binding.SignUpButton.setOnClickListener{
            var name = binding.nameInputField.text.toString()
            var email = binding.emailInputField.text.toString()
            var password =  binding.passwordInputField.text.toString()

            createNewUser(name,email,password)

        }
        return binding.root

      }

    private fun createNewUser(name:String,email:String,password:String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {

                     var user = hashMapOf(
                        "email" to email,
                        "name" to name
                    )
                    addUsertoDB(user)
                    Toast.makeText(this.context, "new User Created", Toast.LENGTH_SHORT).show()
                    startFileIntent()

                 } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this.context, "Failed "+task.exception, Toast.LENGTH_SHORT).show()
           }


            }

    }

    private fun addUsertoDB(user:HashMap<String,String>) {
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("DroidHub", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("DroidHub", "Error adding document", e)
            }
    }

    private fun startFileIntent() {

        val filesIntent = Intent(context,FilesActivity::class.java)
        startActivity(filesIntent)
    }


}
