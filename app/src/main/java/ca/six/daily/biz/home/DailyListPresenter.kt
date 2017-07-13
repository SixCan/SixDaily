package ca.six.daily.biz.home

import ca.six.daily.biz.home.viewmodel.ListItemViewModel
import ca.six.daily.biz.home.viewmodel.ListTitleViewModel
import ca.six.daily.core.network.HttpEngine
import ca.six.daily.data.DailyListResponse
import ca.six.daily.view.ViewType

class DailyListPresenter(val view: IDailyListView) {
    lateinit var listData : DailyListResponse
    var ids : MutableList<Long> = ArrayList()

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

        // refresh the id list after each get a new list data
        ids.clear()
        listData.stories.forEach{ story ->
            ids.add(story.id)
        }

    }

    fun jumpToDetail(position: Int) {
        // TODO handle this IndexOutOfBoundsException, and test it too
        val story = listData.stories[position]
        val idArray : Array<Long> = ids.toTypedArray()
        view.jumpToDetilsPage(story.id, idArray)
    }

}

