package com.example.saiga.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.saiga.R
import com.example.saiga.databinding.FragmentLoginBinding
import com.example.saiga.ui.models.LoginRequestData
import com.example.saiga.ui.networking.ApiClient
import com.example.saiga.ui.networking.ApiService

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var apiService: ApiService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        apiService = ApiClient.getRetrofit().create(ApiService::class.java)

        initVariables()
        initListeners()
    }

    private fun initVariables() {

    }

    private fun initListeners() {

        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
        }

        binding.btnLogIn.setOnClickListener {
            val phoneNum = binding.etInputPhoneNum.text.toString()

            lifecycleScope.launchWhenResumed {
                val response = apiService.authLogin(LoginRequestData(phoneNum))
                if (response.isSuccessful){
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                }else{
                    Toast.makeText(requireContext(),"User not found!",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}