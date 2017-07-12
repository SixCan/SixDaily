package ca.six.daily.biz.detail

import android.os.Bundle
import ca.six.daily.R
import ca.six.daily.core.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_daily_detail.*

// 详情页(id来自list屏)： https://news-at.zhihu.com/api/4/news/3892357   (应该是用WebView加载)
class DailyDetailActivity : BaseActivity(), IDailyDetailView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_detail)

        wvContent.setInitialScale(230)
        wvContent.settings.textZoom = 150
        val presenter = DailyDetailPresenter(this)
        presenter.getDetails("3892357")
    }

    override fun updateDetails(details : HashMap<String, String>) {
        Picasso.with(this)
                .load(details.get("image"))
                .into(ivBanner)

        wvContent.loadData(details.get("body"), "text/html", "utf-8")
    }

}