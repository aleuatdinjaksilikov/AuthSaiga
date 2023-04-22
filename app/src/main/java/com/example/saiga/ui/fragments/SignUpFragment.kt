package com.example.saiga.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.saiga.R
import com.example.saiga.databinding.FragmentSignupBinding
import com.example.saiga.ui.models.SignUpRequestData
import com.example.saiga.ui.networking.ApiClient
import com.example.saiga.ui.networking.ApiService

class SignUpFragment : Fragment(R.layout.fragment_signup) {
    private lateinit var binding: FragmentSignupBinding
    private lateinit var apiService: ApiService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignupBinding.bind(view)
        apiService = ApiClient.getRetrofit().create(ApiService::class.java)

        initListeners()

    }

    private fun initListeners() {


        binding.btnSignUpRegister.setOnClickListener {
            val name = binding.etInputName.text.toString()
            val phoneNumber = binding.etInputPhoneNum.text.toString()
            if (name.isNotEmpty() && phoneNumber.isNotEmpty()){
                lifecycleScope.launchWhenResumed {
                    val response = apiService.createUser(SignUpRequestData(
                        phoneNumber = phoneNumber,
                        firstName = name,
                        lastName = name,
                        role = "USER"
                    ))
                    if (response.isSuccessful){
                        findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToHomeFragment())
                        Toast.makeText(requireContext(), "Created Account", Toast.LENGTH_SHORT).show()
                    }else{
                        Log.d("TTT", "initListeners: onFailure Sign Up")
                    }
                }
            }else{
                Toast.makeText(requireContext(), "Fill in the field!", Toast.LENGTH_SHORT).show()
            }

        }


    }
}