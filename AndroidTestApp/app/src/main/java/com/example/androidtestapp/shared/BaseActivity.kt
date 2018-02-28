package com.example.androidtestapp.shared

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by akiyama on 2018/02/26.
 */
open class BaseActivity: AppCompatActivity() {

    private var loadingDialog: LoadingDialogFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    //
    // Loading dialog
    //

    fun showLoadingDialog(message: String? = null) {
        if (isFinishing || loadingDialog != null) { return }

        loadingDialog = LoadingDialogFragment.newInstance(message)
        loadingDialog?.isCancelable = false
        loadingDialog?.show(supportFragmentManager, "")
    }

    fun dismissLoadingDialog() {
        if (isFinishing) { return }

        loadingDialog?.dismissAllowingStateLoss()
        loadingDialog = null
    }

    //
    // Http request
    //
}