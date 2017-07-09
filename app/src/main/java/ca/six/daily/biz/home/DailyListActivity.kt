package ca.six.daily.biz.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import ca.six.daily.R
import ca.six.daily.biz.home.viewmodel.ListItemViewModel
import ca.six.daily.biz.home.viewmodel.ListTitleViewModel
import ca.six.daily.core.BaseActivity
import ca.six.daily.core.network.HttpEngine
import ca.six.daily.data.DailyListResponse
import ca.six.daily.view.ViewType
import kotlinx.android.synthetic.main.activity_daily_list.*

// 第一屏内容(banner与list) ： https://news-at.zhihu.com/api/4/news/latest
class DailyListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_list)

        rvDailyList.layoutManager = LinearLayoutManager(this)

        val data : MutableList<ViewType> = ArrayList()

        HttpEngine.request("news/latest")
                .map { DailyListResponse(it) }
                .map {
                    data.add(ListTitleViewModel(it.date))
                    it.stories.forEach{ story ->
                        data.add(ListItemViewModel(story.toString()))
                    }
                }
                .subscribe{
                    rvDailyList.adapter = DailyListAdapter(data)
                }



    }

}
