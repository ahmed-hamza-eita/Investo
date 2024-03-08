package com.investoteam.investo.ui.auth.fragments;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.investoteam.investo.databinding.FragmentLoginBinding
import com.investoteam.investo.utils.BindingFragment


class LoginFragment : BindingFragment<FragmentLoginBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentLoginBinding::inflate

    //private val vm: LoginViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}