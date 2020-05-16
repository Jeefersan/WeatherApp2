package com.jeefersan.weatherapp.presentation.about

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.Observer
import com.jeefersan.weatherapp.R
import com.jeefersan.weatherapp.databinding.FragmentAboutBinding
import com.jeefersan.weatherapp.presentation.base.BaseFragment
import com.jeefersan.weatherapp.presentation.base.BaseViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by JeeferSan on 16-5-20.
 */

class AboutFragment : BaseFragment<FragmentAboutBinding>(){
    private val viewModel: AboutViewModelImpl by viewModel()

    override val layoutId: Int = R.layout.fragment_about

    override fun getViewModel(): BaseViewModel = viewModel

    override fun setupBinding() {
        getBinding().apply {
            vm = viewModel
            lifecycleOwner = this@AboutFragment
        }

    }

    override fun initObservers() {
        super.initObservers()
        viewModel.isEmailClicked.observe(this, Observer {
            sendEmailIntent()
        })
    }

    private fun sendEmailIntent(){
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            val mailto = "mailto: ${getString(R.string.my_email)}"
            data = Uri.parse(mailto)
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.my_subject))
        }
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }
}