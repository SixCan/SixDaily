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

class DailyListPresenterTest {
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

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }


    @Test
    fun requestData() {
        val rawResp = "{\"date\":\"20170710\",\"stories\":[{\"images\":[\"a.jpg\"],\"type\":0,\"id\":9517717,\"ga_prefix\":\"071022\",\"title\":\"小事 · 临行密密缝\"}]}"
        HttpEngine.mockJson = rawResp
        HttpEngine.isMock = true

        presenter .requestData()

    }

    @After
    fun tearDown(){
        HttpEngine.isMock = false
        HttpEngine.mockJson = ""
    }

}

