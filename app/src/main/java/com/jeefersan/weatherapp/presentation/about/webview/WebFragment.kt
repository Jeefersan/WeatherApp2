package com.jeefersan.weatherapp.presentation.about.webview

import androidx.navigation.fragment.navArgs
import com.jeefersan.weatherapp.databinding.FragmentWebviewBinding
import com.jeefersan.weatherapp.presentation.base.BaseFragment
import com.jeefersan.weatherapp.presentation.base.BaseViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * Created by JeeferSan on 16-5-20.
 */

class WebFragment : BaseFragment<FragmentWebviewBinding>(){
    private val args: WebFragmentArgs by navArgs()

    private val viewModel: WebViewModelImpl by viewModel{
        parametersOf(args.url)
    }

    override val layoutId: Int = com.jeefersan.weatherapp.R.layout.fragment_webview

    override fun getViewModel(): BaseViewModel = viewModel

    override fun setupBinding() {
        getBinding().apply {
            vm = viewModel
            lifecycleOwner = this@WebFragment
        }
    }

}