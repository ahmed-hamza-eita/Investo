package com.investoteam.investo.utils;

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding

abstract class BindingFragment<out T : ViewBinding> : Fragment() {

    private var _binding: ViewBinding? = null

    @Suppress("UNCHECKED_CAST")
    protected val binding: T
        get() = _binding as T
    var myContext: Context? = null
    var myView: View? = null
    var myActivity: FragmentActivity? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(inflater)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myView = view
        myActivity = requireActivity()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected abstract val bindingInflater: (LayoutInflater) -> ViewBinding
    protected fun navigate(navDirections: NavDirections?) {
        Navigation.findNavController(myView!!).navigate(navDirections!!)
    }


}