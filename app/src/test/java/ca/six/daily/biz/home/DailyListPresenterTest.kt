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
import org.mockito.Mockito

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

        RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }


    @Test
    fun testRequestData() {
        HttpEngine.isMock = true
        HttpEngine.mockJson = "{\"date\":\"20170710\",\"stories\":[{\"images\":[\"https://pic3.zhimg.com/v2-604f4f03fc22ce1bf59788e20aefd646.jpg\"],\"type\":0,\"id\":9517717,\"ga_prefix\":\"071022\",\"title\":\"小事 · 临行密密缝\"}]}"

        presenter.requestData()
        Mockito.verify(view).refresh(Mockito.anyList())

    }

    @After
    fun tearDown(){
        HttpEngine.isMock = false
        HttpEngine.mockJson = ""
    }

}
