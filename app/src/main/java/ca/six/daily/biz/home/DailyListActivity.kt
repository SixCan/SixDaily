package ca.six.daily.biz.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import ca.six.daily.R
import ca.six.daily.biz.home.model.ListItemView
import ca.six.daily.biz.home.model.TitleView
import ca.six.daily.core.BaseActivity
import ca.six.daily.view.ViewType
import kotlinx.android.synthetic.main.activity_daily_list.*

// 第一屏内容(banner与list) ： https://news-at.zhihu.com/api/4/news/latest
class DailyListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_list)

//        HttpEngine.request("news/latest")
//                .subscribe{str -> println("szw rx : ret = $str")}

        val data : MutableList<ViewType> = ArrayList()
        data.add(TitleView("July 8th, 2017"))
        data.add(ListItemView("001"))
        data.add(ListItemView("002"))
        data.add(ListItemView("003"))
        data.add(TitleView("July 7th, 2017"))
        data.add(ListItemView("A01"))
        data.add(ListItemView("A02"))
        data.add(ListItemView("A03"))


        rvDailyList.layoutManager = LinearLayoutManager(this)
        rvDailyList.adapter = DailyListAdapter(data)
    }

}
