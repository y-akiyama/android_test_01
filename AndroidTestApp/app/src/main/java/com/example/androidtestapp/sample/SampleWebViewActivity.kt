package com.example.androidtestapp.sample

import android.os.Bundle
import android.view.MenuItem
import com.example.androidtestapp.shared.BaseFragmentActivity

/**
 * Created by akiyama on 2018/02/27.
 */
class SampleWebViewActivity : BaseFragmentActivity() {

    companion object {
        val INTENT_KEY_URL = "INTENT_KEY_URL"
        val BUNDLE_KEY_URL = "BUNDLE_KEY_URL"
        val INTENT_KEY_TITLE = "INTENT_KEY_TITLE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)

            val title = intent?.getStringExtra(INTENT_KEY_TITLE) ?: ""
            setTitle(title)
        }

        if (savedInstanceState == null) {
            val fragment = SampleWebViewFragment().apply {
                val urlStr = intent?.getStringExtra(INTENT_KEY_URL) ?: ""
                arguments = Bundle().apply { putString(BUNDLE_KEY_URL, urlStr) }
            }
            showFragment(fragment, addToBackStack = false)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }
}