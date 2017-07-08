package ca.six.daily.core

import android.app.Application
import android.content.Context
import okhttp3.OkHttpClient
import kotlin.properties.Delegates

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        app = this;
        http = OkHttpClient()
    }

    companion object{
        var app : Context by Delegates.notNull<Context>()
        var http : OkHttpClient by Delegates.notNull<OkHttpClient>()
    }
}
