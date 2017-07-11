package ca.six.daily.core.network

import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Response
import okhttp3.ResponseBody

class MockResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if(HttpEngine.isMock) {
            val jsonContent = HttpEngine.mockJson
            val responseBody = ResponseBody.create(MediaType.parse("application/x-www-form-urlencoded"), jsonContent)
            return Response.Builder()
                    .body(responseBody)
                    .build()
        } else {
            return chain.proceed(chain.request())
        }
    }
}
