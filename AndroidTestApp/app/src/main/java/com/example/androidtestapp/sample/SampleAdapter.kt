package com.example.androidtestapp.sample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.androidtestapp.R
import com.example.androidtestapp.shared.Item
import kotlinx.android.synthetic.main.sample_list_item_main.view.*

/**
 * Created by akiyama on 2018/02/27.
 */
class SampleAdapter(val context: Context): BaseAdapter() {
    private var items = listOf<Item>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.sample_list_item_main, null)

        view.apply {
            val item = items[position]

            list_item_title.text = item.title
            list_item_pub_date.text = item.pubDate
        }

        return view
    }

    override fun getItem(position: Int): Item {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return items.count()
    }

    fun updateList(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

}