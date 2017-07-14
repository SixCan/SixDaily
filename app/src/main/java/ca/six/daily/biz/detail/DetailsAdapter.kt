package ca.six.daily.biz.detail

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import ca.six.daily.R

/**
 * @author hellenxu
 * @date 2017-07-13
 * Copyright 2017 Six. All rights reserved.
 */
class DetailsAdapter(val ctx: Context, val ids: List<*>, val selectedId: Long) : PagerAdapter() {
    private val inflater = LayoutInflater.from(ctx) as LayoutInflater
    private var ivBanner:ImageView? = null
    private var wvContent:WebView? = null

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        println("init: $position")
        val itemView: View = inflater.inflate(R.layout.item_daily_details, container, false)
        ivBanner = itemView.findViewById(R.id.ivBanner) as ImageView
        wvContent = itemView.findViewById(R.id.wvContent) as WebView
        val pos = ids.indexOf(selectedId)
        container.addView(itemView, position)
        return itemView
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return ids.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        println("destroyItem: $position")
        container.removeViewAt(position)
    }
}