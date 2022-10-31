package com.example.formulaoneapplicationn.ui.signInFragment

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.example.formulaone.databinding.FragmentSignInBinding
import com.example.formulaone.common.bases.BaseFragment
import com.example.formulaoneapplicationn.common.bases.BaseFragment
import com.example.formulaoneapplicationn.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class SignInFragment : BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::inflate) {

    private lateinit var auth: FirebaseAuth

    override fun viewCreated() {
        auth = Firebase.auth


        binding.etEmailImpl.doOnTextChanged { text, start, before, count ->
            binding.btnSignIn.visibility = View.VISIBLE
        }
    }

    override fun listeners() {
        binding.btnSignIn.setOnClickListener {
            loginWithUser()
        }
    }
    private fun loginWithUser(){
        val email = binding.etEmailImpl.text.toString()
        val password = binding.etPasswordImpl.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty()){
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.signInWithEmailAndPassword(email,password).await()
                    withContext(Dispatchers.Main){
                        checkLoggedInState()
                        findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToMainFragment())
                    }
                }catch (e:Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(requireContext(),"wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun checkLoggedInState() {
        val user = auth.currentUser
        if (user == null) {
            Log.d("mcici","not logged in")
        } else {
            Log.d("mcici","logged in")
        }
    }


}