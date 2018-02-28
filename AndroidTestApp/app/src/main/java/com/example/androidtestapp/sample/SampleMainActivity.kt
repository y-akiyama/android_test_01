package com.example.androidtestapp.sample

import android.os.Bundle
import com.example.androidtestapp.shared.BaseFragmentActivity

/**
 * Created by akiyama on 2018/02/27.
 */
class SampleMainActivity : BaseFragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            showFragment(SampleMainFragment(),false)
        }
    }

}