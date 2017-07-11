package ca.six.daily.biz.detail

import android.os.Bundle
import ca.six.daily.R
import ca.six.daily.core.BaseActivity

// 详情页(id来自list屏)： https://news-at.zhihu.com/api/4/news/3892357   (应该是用WebView加载)
class DailyDetailActivity : BaseActivity(), IDailyDetailView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_detail)
    }

    override fun updateDetails(details : HashMap<String, String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}