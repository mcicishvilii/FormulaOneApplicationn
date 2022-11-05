package com.example.formulaoneapplicationn.ui.welcomeFragment

import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.example.formulaone.R
import com.example.formulaone.databinding.FragmentWelcomeBinding
import com.example.formulaone.common.bases.BaseFragment
import com.example.formulaoneapplicationn.R
import com.example.formulaoneapplicationn.common.bases.BaseFragment
import com.example.formulaoneapplicationn.databinding.FragmentWelcomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        checkLoggedInState()
    }

    override fun viewCreated() {

    }

    override fun listeners() {
        binding.btnCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_createAccountFragment)
        }

        binding.tvSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_signInFragment)
        }

        binding.tvNotNow.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_mainFragment)
        }


    }

    private fun checkLoggedInState() {
        val user = auth.currentUser
        if (user == null) {
            Log.d("mcici","not logged in")
        } else {
            Log.d("mcici", "logged in")
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToMainFragment())
        }
    }


}