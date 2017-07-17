package ca.six.daily.biz.detail

import android.os.Bundle
import ca.six.daily.R
import ca.six.daily.core.BaseActivity
import kotlinx.android.synthetic.main.activity_daily_detail.*
import java.util.*

// 详情页(id来自list屏)： https://news-at.zhihu.com/api/4/news/3892357   (应该是用WebView加载)
class DailyDetailActivity : BaseActivity(), IDailyDetailView {
    var selectedId: Long = 0
    var ids: List<Long> = ArrayList<Long>()
    val DEFAULT_ID = 3892357
    var adapter: DailyDetailsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_detail)
        selectedId = intent.getLongExtra("it_detailID", DEFAULT_ID.toLong())
        ids = intent.getLongArrayExtra("it_detailID_array").asList()
        println("chosen id: $selectedId")
        ids.forEach {
            println("id: $it")
        }

//        wvContent.setInitialScale(230)
//        wvContent.settings.textZoom = 150
        adapter = DailyDetailsAdapter(this, ids, selectedId)
        vpList.adapter = adapter
    }

    override fun updateDetails(details: HashMap<String, String>) {

    }

    //TODO
    override fun scrollToPosition(pos: Int) {
    }

}