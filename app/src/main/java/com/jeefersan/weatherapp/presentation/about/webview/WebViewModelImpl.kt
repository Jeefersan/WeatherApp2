package com.jeefersan.weatherapp.presentation.about.webview

import android.graphics.Bitmap
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jeefersan.weatherapp.presentation.base.BaseViewModel
import com.jeefersan.weatherapp.presentation.base.LoadingStatus

class WebViewModelImpl(uri: String) : WebViewModel, BaseViewModel() {
    private val _webUri = MutableLiveData<String>()
    override val webUri: LiveData<String> = _webUri

    var webClient: WebClient

    init {
        setStatus(LoadingStatus.LOADING)
        _webUri.value = uri
        webClient = WebClient()
    }


    inner class WebClient : WebViewClient() {

        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            setStatus(LoadingStatus.ERROR)
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            setStatus(LoadingStatus.DONE)
        }


    }


}