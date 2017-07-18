package ca.six.daily.biz.detail

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import ca.six.daily.R
import ca.six.daily.view.RvViewHolder
import com.squareup.picasso.Picasso

/**
 * @author hellenxu
 * @date 2017-07-17
 * Copyright 2017 Six. All rights reserved.
 */
class RvDetailsAdapter(val ctx: Context, val ids: List<Long>, selectedId: Long) :
        RecyclerView.Adapter<RvViewHolder>(), IDailyDetailView {
    private var data = ArrayList<HashMap<String, String>>()
    private val presenter: DailyDetailPresenter = DailyDetailPresenter(this)
    private var currentPos: Int = 0

    init {
        presenter.getDetails(selectedId)
        ids.forEach {
            val item = HashMap<String, String>()
            data.add(item)
        }
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        val size = data.size
        val banner = holder.getView<ImageView>(R.id.ivBanner)
        val content = holder.getView<WebView>(R.id.wvContent)
        if (size > 0 && position < size) {
            val item = data[position]
            Picasso.with(ctx)
                    .load(item["image"])
                    .placeholder(R.drawable.loading_placeholder)
                    .into(banner)
            content.loadData(item["body"], "text/html", "utf-8")
        }
    }

    override fun getItemCount(): Int {
        return  ids.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        return RvViewHolder.createViewHolder(parent, R.layout.item_daily_details)
    }

    override fun updateDetails(details: HashMap<String, String>) {
        data[currentPos] = details
        notifyDataSetChanged()
    }

    fun changeCurrentPosition(pos: Int) {
        val selectedId = ids[pos].toString()
        var isCached = false
        data.forEach {
            if(it["id"].equals(selectedId)) {
                isCached = true
            }
        }
        if(!isCached) {
            presenter.getDetails(selectedId.toLong())
        }

        currentPos = pos
    }
}