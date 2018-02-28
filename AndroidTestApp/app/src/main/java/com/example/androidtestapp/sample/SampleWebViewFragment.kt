package com.example.androidtestapp.sample

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import com.example.androidtestapp.R
import kotlinx.android.synthetic.main.sample_fragment_web_view.view.*

/**
 * Created by akiyama on 2018/02/27.
 */
class SampleWebViewFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val res = inflater.inflate(R.layout.sample_fragment_web_view, container, false)

        val url = arguments?.getString(SampleWebViewActivity.BUNDLE_KEY_URL, "")
        res.sample_web_view?.apply {
            settings.javaScriptEnabled = true
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }

            loadUrl(url)
        }

        return res
    }
}