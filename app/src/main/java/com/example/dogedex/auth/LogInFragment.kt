package com.example.dogedex.auth

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dogedex.R
import com.example.dogedex.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {

    interface LoginFragmentActions {
        fun onRegisterButtonClick()
    }

    private lateinit var loginFragmentActions: LoginFragmentActions

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loginFragmentActions = try {
            context as LoginFragmentActions
        } catch (e: java.lang.ClassCastException) {
            throw ClassCastException("$context must implements LoginFragmentActions")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLogInBinding.inflate(layoutInflater)
        binding.loginRegisterButton.setOnClickListener {
            loginFragmentActions.onRegisterButtonClick()
        }
        return binding.root
    }

}