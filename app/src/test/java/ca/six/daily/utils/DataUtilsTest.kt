
package ca.six.daily.utils

import android.os.Build
import ca.six.daily.BuildConfig
import ca.six.daily.core.BaseApp
import ca.six.daily.utils.isCacheFileExist
import ca.six.daily.utils.writeToCacheFile
import org.junit.After
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowEnvironment
import java.io.File

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(Build.VERSION_CODES.LOLLIPOP)
        ,application = BaseApp::class)
class DataUtilsTest {
    val fileName = "news_latest.json"

    @Test
    fun testIsExisting_returnFalse_whenAtFirst(){
        assertFalse(isCacheFileExist(fileName))
    }

    @Test
    fun testCacheFile_whenSuccess() {
        writeToCacheFile("test", fileName)
        assertTrue(isCacheFileExist(fileName))
        assertEquals("test", readCacheFile(fileName))
    }

    @Test
    fun testReadCachedLatestNews_whenNoCache_shouldObserveComplete(){

    }

    @Test
    fun testReadCachedLatestNews_whenLatestCache_shouldObserveNext(){

    }

    @Test
    fun testReadCachedLatestNews_whenCacheIsOld_shouldObserveComplete(){

    }

    private fun prepareCacheFile(date : Int){
        var fileContent = "{ date: \"${date}\", stories: [] }"

    }

    // delete the file, so the test is stateless
    @After
    fun cleanUp(){
        // dir.path = "/var/folders/4w/1qcx1th56px_3vvfmbcy2zdh0000gp/T/robolectric695337270918799239/cache"
        val dir = BaseApp.app.cacheDir
        val file = File(dir, fileName)
        file.delete()
    }

}

/*
1. use Robolectric to avoid error:
"Property 'app' should be initialzied before get" : BaseApp.app

2. Robolectric + AndroidStudio3.x : NPE Error
: to fix it,  add "android.enableAapt2=false" in gradle.properties
*/