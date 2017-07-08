package ca.six.daily.core.network

import okhttp3.Request
import okhttp3.OkHttpClient

object HttpEngine {
    val PREFIX = " https://news-at.zhihu.com/api/4/"

    fun request(http: OkHttpClient, end : String): String {
        val req = Request.Builder()
                .url(PREFIX + end)
                .build()
        val resp = http.newCall(req).execute()
        return resp.body()?.string() ?: "" // 三目运算符
    }
}