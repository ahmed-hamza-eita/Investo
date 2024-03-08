package com.investoteam.investo.ui.auth.fragments;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.investoteam.investo.databinding.FragmentAuthBinding
import com.investoteam.investo.utils.BindingFragment


class AuthFragment : BindingFragment<FragmentAuthBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentAuthBinding::inflate

    //private val vm: LoginViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}