package ca.six.daily.biz.detail

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import ca.six.daily.R
import ca.six.daily.core.BaseActivity
import kotlinx.android.synthetic.main.activity_daily_detail.*
import java.util.*

// 详情页(id来自list屏)： https://news-at.zhihu.com/api/4/news/3892357   (应该是用WebView加载)
class DailyDetailActivity : BaseActivity() {
    var selectedId: Long = 0
    var ids: List<Long> = ArrayList<Long>()
    var adapter: RvDetailsAdapter? = null
    var layoutManager: LinearLayoutManager? = null

    val DEFAULT_ID = 3892357

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_detail)
        selectedId = intent.getLongExtra("it_detailID", DEFAULT_ID.toLong())
        ids = intent.getLongArrayExtra("it_detailID_array").asList()
        println("chosen id: $selectedId")

        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvList.layoutManager = layoutManager
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rvList)
        adapter = RvDetailsAdapter(this, ids, selectedId)
        rvList.adapter = adapter
        rvList.addOnScrollListener(DetailsOnScrollListener())

    }

    inner class DetailsOnScrollListener : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
            when(newState) {
                RecyclerView.SCROLL_STATE_IDLE -> {
                    val currentPos = layoutManager?.findFirstVisibleItemPosition() ?: 0
                    adapter?.changeCurrentPosition(currentPos)
                    println("xxl-idle: $currentPos")
                }
            }
            super.onScrollStateChanged(recyclerView, newState)
        }
    }
}