package com.example.dogedex.auth

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dogedex.FormValidations
import com.example.dogedex.R
import com.example.dogedex.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    interface SigUpFragmentActions {
        fun onSigUpFieldsValidated(
            email: String,
            password: String,
            confirmPassword: String,
        )
    }

    private lateinit var signUpFragmentActions: SigUpFragmentActions
    private lateinit var binding: FragmentSignUpBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        signUpFragmentActions = try {
            context as SigUpFragmentActions
        } catch (e: java.lang.ClassCastException) {
            throw ClassCastException("$context must implements SigUpFragmentActions")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        setUpSignUpButton()
        return binding.root
    }

    private fun setUpSignUpButton() {
        binding.signUpButton.setOnClickListener {
            validateFields()
        }
    }

    private fun validateFields() {
        binding.emailInput.error = ""
        binding.passwordInput.error = ""
        binding.confirmPasswordInput.error = ""
        val email = binding.emailEdit.text.toString()
        if (!FormValidations.isValidEmail(email)) {
            binding.emailInput.error = getString(R.string.email_is_not_valid)
            return
        }

        val password = binding.passwordEdit.text.toString()
        if (password.isEmpty()) {
            binding.passwordInput.error = getString(R.string.password_must_not_be_empty)
            return
        }

        val confirmPassword = binding.confirmPasswordEdit.text.toString()
        if (confirmPassword.isEmpty()) {
            binding.confirmPasswordEdit.error = getString(R.string.password_must_not_be_empty)
            return
        }

        if (password != confirmPassword) {
            binding.passwordInput.error = getString(R.string.password_do_not_match)
            return
        }

        signUpFragmentActions.onSigUpFieldsValidated(email, password, confirmPassword)
    }

}