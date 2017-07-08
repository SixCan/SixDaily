package ca.six.daily.core

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        app = this;
    }

    companion object{
        var app : Context by Delegates.notNull<Context>()
    }
}
