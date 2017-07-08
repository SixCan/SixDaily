package ca.six.daily.core.network

import okhttp3.Request
import okhttp3.OkHttpClient

object HttpEngine {
    val PREFIX = " https://news-at.zhihu.com/api/4/"

    fun rquest(http: OkHttpClient, end : String): String {
        val req = Request.Builder()
                .url(PREFIX + end) //PREFIX + "news/latest"
                .build()
        val resp = http.newCall(req).execute()
        return resp.body()?.string() ?: ""
    }
}