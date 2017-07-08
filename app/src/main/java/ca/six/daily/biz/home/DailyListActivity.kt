package ca.six.daily.biz.home

import android.os.Bundle
import ca.six.daily.R
import ca.six.daily.core.BaseActivity

// 第一屏内容(banner与list) ： https://news-at.zhihu.com/api/4/news/latest
class DailyListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_list)
    }

}
