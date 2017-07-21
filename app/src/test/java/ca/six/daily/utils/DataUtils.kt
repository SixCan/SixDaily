package ca.six.daily.utils

import android.os.Build
import ca.six.daily.BuildConfig
import ca.six.daily.core.BaseApp
import ca.six.daily.utils.isCacheFileExist
import ca.six.daily.utils.writeToCacheFile
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowEnvironment

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(Build.VERSION_CODES.LOLLIPOP)
        ,application = BaseApp::class)
class DataUtilsTest {
    val fileName = "temp.txt"

    @Test
    fun testCacheFile_whenSuccess() {
        println("szw app = ${BaseApp.app}")

    }
}

/*
1. use Robolectric to avoid error:
"Property 'app' should be initialzied before get" : BaseApp.app


*/
