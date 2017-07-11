package ca.six.daily.core.network

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Request
import okhttp3.OkHttpClient

object HttpEngine {
    val PREFIX = " https://news-at.zhihu.com/api/4/"
    val http : OkHttpClient by lazy {
        OkHttpClient.Builder()
                .addInterceptor(MockResponseInterceptor())
                .build()
    }

    // for test
    var isMock = false
    var mockJson = ""

    fun requestString(http: OkHttpClient, end: String): String {
        val req = Request.Builder()
                .url(PREFIX + end)
                .build()
        val resp = http.newCall(req).execute()
        return resp.body()?.string() ?: "" // 三目运算符
    }

    fun request(end: String): Observable<String> {
        val observable = Observable.create<String> { emitter ->
            val responseString = requestString(http, end)
            println("szw resp str = $responseString")
            emitter.onNext(responseString)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        return observable
    }
}
