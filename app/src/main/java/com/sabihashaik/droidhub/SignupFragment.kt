package com.sabihashaik.droidhub

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.sabihashaik.droidhub.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {
   lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container,false)

        binding.LoginButton.setOnClickListener{view:View->
            view.findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }

        binding.SignUpButton.setOnClickListener{
            val filesIntent = Intent(context,FilesActivity::class.java)
            startActivity(filesIntent)
        }
        return binding.root

      }

    
}