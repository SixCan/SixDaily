package ca.six.daily.biz.home

import android.os.Bundle
import ca.six.daily.R
import ca.six.daily.core.BaseActivity
import ca.six.daily.core.BaseApp
import ca.six.daily.core.network.HttpEngine
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


// 第一屏内容(banner与list) ： https://news-at.zhihu.com/api/4/news/latest
class DailyListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_list)

        Observable.create<String> { emitter ->
                    val req = HttpEngine.request(BaseApp.http,"news/latest")
                    emitter.onNext(req)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{str -> println("szw rx : ret = $str")}
    }

}
