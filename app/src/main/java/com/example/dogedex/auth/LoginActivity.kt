package com.example.dogedex.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.dogedex.R

class LoginActivity : AppCompatActivity(), LogInFragment.LoginFragmentActions, SignUpFragment.SigUpFragmentActions {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onRegisterButtonClick() {
        findNavController(R.id.nav_host_fragment)
            .navigate(LogInFragmentDirections.actionLogInFragmentToSignUpFragment())
    }

    override fun onSigUpFieldsValidated(
        email: String,
        password: String,
        confirmPassword: String
    ) {
        TODO("Not yet implemented")
    }
}