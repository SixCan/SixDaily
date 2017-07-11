package ca.six.daily.biz.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import ca.six.daily.R
import ca.six.daily.core.BaseActivity
import ca.six.daily.view.ViewType
import ca.six.daily.view.WhiteSpaceDivider
import kotlinx.android.synthetic.main.activity_daily_list.*

// 第一屏内容(banner与list) ： https://news-at.zhihu.com/api/4/news/latest
class DailyListActivity : BaseActivity(), IDailyListView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_list)

        rvDailyList.layoutManager = LinearLayoutManager(this)
        rvDailyList.addItemDecoration(WhiteSpaceDivider())

        val presenter = DailyListPresenter(this)
        presenter.requestData()
    }

    override fun refresh(data: MutableList<ViewType>) {
        rvDailyList.adapter = DailyListAdapter(data)
    }

}
