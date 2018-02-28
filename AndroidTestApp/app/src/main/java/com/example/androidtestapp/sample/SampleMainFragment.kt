package com.example.androidtestapp.sample

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidtestapp.R
import com.example.androidtestapp.shared.HttpRequest
import kotlinx.android.synthetic.main.sample_fragment_main.*
import kotlinx.android.synthetic.main.sample_fragment_main.view.*

/**
 * Created by akiyama on 2018/02/27.
 */
class SampleMainFragment: Fragment(), TabLayout.OnTabSelectedListener {

    private var mAdapter: SampleAdapter? = null

    private val tabItems = listOf<Pair<String, String>>(
        Pair("主要", "")
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val res = inflater.inflate(R.layout.sample_fragment_main, container, false)

        mAdapter = SampleAdapter(activity)
        res.main_list_view?.apply {
            adapter = mAdapter
            setOnItemClickListener { parent, view, position, id ->
                val item = mAdapter?.getItem(position)

                val intent = Intent(activity, SampleWebViewActivity::class.java).apply {
                    putExtra(SampleWebViewActivity.INTENT_KEY_URL, item?.link ?: "")
                    putExtra(SampleWebViewActivity.INTENT_KEY_TITLE, item?.title ?: "")
                }
                startActivity(intent)
            }
        }

        res.refresh?.let { it.setOnRefreshListener { onRefresh(it) } }

        res.main_tab_layout?.apply {
            tabItems.forEach { addTab(newTab().setText(it.first)) }
            addOnTabSelectedListener(this@SampleMainFragment)
        }

        return res
    }

    override fun onResume() {
        super.onResume()

        httpRequest()
    }

    private fun httpRequest(category: String = "", completion: (()->Unit)? = null) {
        HttpRequest.call(category, { res ->
            res?.toResponseModel()?.channel?.items?.let {
                mAdapter?.updateList(it)
                completion?.invoke()
            }
        }, {
        })
    }

    private fun onRefresh(refresh: SwipeRefreshLayout) {
        val tabPosition = main_tab_layout?.selectedTabPosition ?: 0
        val category = tabItems[tabPosition].second
        httpRequest(category, {
            refresh.isRefreshing = false
        })
    }

    //
    // OnTabSelectedListener
    //
    override fun onTabSelected(tab: TabLayout.Tab?) {
        val tabPosition = tab?.position ?: 0
        val category = tabItems[tabPosition].second
        httpRequest(category)
    }
    override fun onTabReselected(tab: TabLayout.Tab?) {}
    override fun onTabUnselected(tab: TabLayout.Tab?) {}
}