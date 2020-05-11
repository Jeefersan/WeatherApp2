package com.jeefersan.weatherapp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
import com.jeefersan.weatherapp.misc.NavigationCommand

/**
 * Created by JeeferSan on 23-4-20.
 */
abstract class BaseFragment<DataBinding : ViewDataBinding> : Fragment() {

    private lateinit var binding: DataBinding

    abstract val layoutId: Int

    abstract fun getViewModel(): BaseViewModel

    abstract fun setupBinding()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBinding()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObservers()
    }


    open fun initObservers(){
        getViewModel().navigation.observe(viewLifecycleOwner, Observer { command ->
            when (command){
                is NavigationCommand.To -> findNavController().navigate(command.directions)
                is NavigationCommand.Back -> findNavController().navigateUp()
            }
        })
        getViewModel().snackbar.observe(viewLifecycleOwner, Observer { message ->
            showSnackbar(message)
        })
    }

    fun showSnackbar(message: String){
        Snackbar.make(binding.root, message, LENGTH_SHORT).show()
    }

    fun getBinding() = binding
}