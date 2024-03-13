package com.investoteam.investo.ui.auth.fragments;

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.investoteam.investo.R
import com.investoteam.investo.data.models.UserModel
import com.investoteam.investo.databinding.FragmentRegisterBinding
import com.investoteam.investo.ui.auth.viewmodels.RegisterViewModel
import com.investoteam.investo.utils.BindingFragment
import com.investoteam.investo.utils.NetworkState
import com.investoteam.investo.utils.ProgressLoading
import com.investoteam.investo.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class RegisterFragment : BindingFragment<FragmentRegisterBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentRegisterBinding::inflate

    private val registerViewModel: RegisterViewModel by viewModels()

    private val cal: Calendar = Calendar.getInstance()
    private var startDateSetListener: DatePickerDialog.OnDateSetListener? = null
    private var birthday = ""

    //private val vm: LoginViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        actions()
        observer()
    }

    private fun observer() {
        registerViewModel.registerLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                NetworkState.Status.RUNNING -> ProgressLoading.show(requireActivity())
                NetworkState.Status.SUCCESS -> {
                    val data = it.data as UserModel
                    ProgressLoading.dismiss()
                    showToast("done")
                }

                else -> {
                    ProgressLoading.dismiss()
                    showToast(it.msg)
                    Log.e(TAG, "observer: ${it.msg}")
                }
            }
        }
    }

    private fun actions() {
        startDateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->

                val realMonth = month + 1

                val myMonth = if (realMonth < 10) "0$realMonth" else realMonth.toString()
                val myDay = if (dayOfMonth < 10) "0$dayOfMonth" else dayOfMonth.toString()

                birthday = "${year}-${myMonth}-${myDay}"
                binding.edtBirthday.text = birthday
            }
        binding.apply {
            btnSignup.setOnClickListener {
                validation()
            }
            edtBirthday.setOnClickListener { datePicker() }
        }
    }

    private fun validation() {
        binding.apply {
            val fName = edtFName.text.toString()
            val lName = edtLName.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            val phone = edtPhone.text.toString()
            val nationalId = edtNationalNumber.text.toString()
            val gender = spinnerGender.selectedItem.toString()
            val type = spinnerType.selectedItem.toString()

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                edtEmail.error = getString(R.string.wrong_email_address)
            } else if (password == "") {
                edtPassword.error = getString(R.string.required)
            } else if (fName == "") {
                edtFName.error = getString(R.string.required)
            } else if (lName == "") {
                edtLName.error = getString(R.string.required)
            } else if (phone == "") {
                edtPhone.error = getString(R.string.required)
            } else if (nationalId == "") {
                edtNationalNumber.error = getString(R.string.required)
            } else if (spinnerGender.selectedItem == 0) {
                showToast(getString(R.string.required))
            } else if (birthday == "") {
                showToast(getString(R.string.required))
            } else {
                registerViewModel.signup(
                    fName, lName, email, password, nationalId, gender, "2002-2-2"
                )

            }
        }
    }

    private fun datePicker() {
        val dialog = DatePickerDialog(
            requireContext(),

            startDateSetListener,
            cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        dialog.show()
    }

    companion object {
        private val TAG = "RegisterFragment"
    }

}