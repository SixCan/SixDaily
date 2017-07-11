package ca.six.daily.biz.home
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import io.reactivex.internal.schedulers.ExecutorScheduler
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import io.reactivex.android.plugins.RxAndroidPlugins

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
        val server = MockWebServer()
        server.enqueue(MockResponse().setBody("hello, world"))
        server.start()
        val baseUrl = server.url("news/latest") //=> http://localhost:53986/news/latest

        presenter.requestData()
//        Mockito.verify(view).refresh(Mockito.any())

        server.shutdown()
    }

}
