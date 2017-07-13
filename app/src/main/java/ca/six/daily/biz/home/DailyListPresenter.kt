package ca.six.daily.biz.home

import ca.six.daily.biz.home.viewmodel.ListItemViewModel
import ca.six.daily.biz.home.viewmodel.ListTitleViewModel
import ca.six.daily.core.network.HttpEngine
import ca.six.daily.data.DailyListResponse
import ca.six.daily.view.ViewType

class DailyListPresenter(val view: IDailyListView) {
    lateinit var listData : DailyListResponse

    fun requestData() {
        val data: MutableList<ViewType> = ArrayList()

        HttpEngine.request("news/latest")
                .map {
                    listData = DailyListResponse(it)
                    listData
                }
                .map {
                    data.add(ListTitleViewModel(it.date))
                    it.stories.forEach { story ->
                        data.add(ListItemViewModel(story))
                    }
                }
                .subscribe { view.refresh(data) }
    }

    fun jumpToDetail(position: Int) {
        val story = listData.stories[position]
        view.jumpToDetilsPage(hashMapOf("it_detailID" to story.id.toString()))
    }
}

