package ca.six.daily.biz.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import ca.six.daily.R
import ca.six.daily.biz.detail.DailyDetailActivity
import ca.six.daily.core.BaseActivity
import ca.six.daily.view.*
import kotlinx.android.synthetic.main.activity_daily_list.*

// 第一屏内容(banner与list) ： https://news-at.zhihu.com/api/4/news/latest
class DailyListActivity : BaseActivity(), IDailyListView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_list)

        rvDailyList.layoutManager = LinearLayoutManager(this)
        rvDailyList.addItemDecoration(WhiteSpaceDivider())

        // for UI holders before we get the real data from server
        val holderLists = arrayListOf<Int>(1, 2, 3, 4)
        val tempAdapter = object : OneAdapter<Int>(R.layout.item_daily_list_holder) {
            override fun apply(vh: RvViewHolder, t: Int, position: Int) {
            }
        }
        tempAdapter.data = holderLists
        rvDailyList.adapter = tempAdapter

        val presenter = DailyListPresenter(this)
        presenter.requestData()

        rvDailyList.addOnItemTouchListener(object : OnRvItemClickListener(rvDailyList){
            override fun onItemClick(holder: RecyclerView.ViewHolder) {
                presenter.jumpToDetail(holder.adapterPosition)
            }
        })
    }

    override fun refresh(data: MutableList<ViewType<out Any>>) {
        rvDailyList.adapter = DailyListAdapter(data)

    }

    override fun jumpToDetilsPage(thisStoryID: Long, allIDs: Array<Long>) {
        val it = Intent(this, DailyDetailActivity::class.java)
        it.putExtra("it_detailID", thisStoryID)
        it.putExtra("it_detailID_array", allIDs)
        startActivity(it)
    }

}
