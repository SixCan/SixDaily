package ca.six.daily.biz.home

import ca.six.daily.core.network.HttpEngine
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import io.reactivex.internal.schedulers.ExecutorScheduler
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import io.reactivex.android.plugins.RxAndroidPlugins
import org.junit.After
import io.reactivex.plugins.RxJavaPlugins

import org.junit.Assert.*
import org.mockito.Mockito.*

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(Build.VERSION_CODES.LOLLIPOP)
        ,application = BaseApp::class)
class DailyListPresenterTest {
    val fileName = "news_latest.json"
    @Mock lateinit var view : IDailyListView
    val presenter : DailyListPresenter by lazy {
        DailyListPresenter(view)
    }

    @Before fun setUp(){
        MockitoAnnotations.initMocks(this)

        val immediate = object : Scheduler() {
            override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Scheduler.Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }

    @Test
    fun testRequestData_whenNoCache_thenGoGetHttpData(){
        tearDown() // to make sure there is no cache locally

        prepareHttpData()
        presenter.requestData()

        verify(view).refresh(anyList())
        assertEquals(1, presenter.ids.size)
        assertEquals(9517717, presenter.ids[0])

    }


    private fun prepareHttpData(){
        HttpEngine.isMock = true
        HttpEngine.mockJson = "{\"date\":\"20170710\",\"stories\":[{\"images\":[\"https://pic3.zhimg.com/v2-604f4f03fc22ce1bf59788e20aefd646.jpg\"],\"type\":0,\"id\":9517717,\"ga_prefix\":\"071022\",\"title\":\"小事 · 临行密密缝\"}]}"
    }

    private fun prepareCacheFile(date : Int){
        var fileContent = "{ date: \"${date}\", stories: [] }"
        writeToCacheFile(fileContent, fileName)
    }

    @After
    fun tearDown(){
        // clear cache data
        val dir = BaseApp.app.cacheDir
        val file = File(dir, fileName)
        file.delete()

        // clear http data
        HttpEngine.isMock = false
        HttpEngine.mockJson = ""
    }

}
//
//    @Test
//    fun testRequestData_gotSuccessfulData_thenRefreshView() {
//        prepareOneStoryStubData()
//
//        presenter.requestData()
//
//        verify(view).refresh(anyList())
//        assertEquals(1, presenter.ids.size)
//        assertEquals(9517717, presenter.ids[0])
//
//    }
//
//    @Test
//    fun testJumpToDetail_gotSuccessuflData_thenClickFirst(){
//        prepareOneStoryStubData()
//
//        presenter.requestData()
//        presenter.jumpToDetail(1) // the first item (index: 0) is a title
//
//        val ids = longArrayOf(9517717L)
//        verify(view).jumpToDetilsPage(9517717, ids)
//    }
//
//    @Test(expected = IndexOutOfBoundsException::class)
//    fun testJumpToDetail_gotSuccessuflData_thenClickWrongPosition(){
//        prepareOneStoryStubData()
//
//        presenter.requestData()
//        presenter.jumpToDetail(10)
//
//    }

