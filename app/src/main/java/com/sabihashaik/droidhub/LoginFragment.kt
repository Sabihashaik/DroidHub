package com.sabihashaik.droidhub

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.sabihashaik.droidhub.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater,container,false)

        binding.orSignupButton.setOnClickListener{view:View ->
            view.findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        binding.orLoginButton.setOnClickListener{
            val filesIntent = Intent(context,FilesActivity::class.java)
            startActivity(filesIntent)

        }
        return binding.root
    }

}