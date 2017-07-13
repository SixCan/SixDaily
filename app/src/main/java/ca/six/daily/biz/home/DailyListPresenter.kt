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
                .map { jsonString ->
                    listData = DailyListResponse(jsonString)
                    listData
                }
                .map { resp ->
                    data.add(ListTitleViewModel(resp.date))
                    resp.stories.forEach { story ->
                        data.add(ListItemViewModel(story))
                    }
                    listData
                }
                .map { resp ->
                    // refresh the id list after each get a new list data
                    ids.clear()
                    resp.stories.forEach{ story ->
                        ids.add(story.id)
                    }
                }
                .subscribe { view.refresh(data) }

    }


    fun jumpToDetail(position: Int) {
        // TODO handle this IndexOutOfBoundsException, and test it too
        val story = listData.stories[position]
        val idArray : Array<Long> = ids.toTypedArray()
        view.jumpToDetilsPage(story.id, idArray)
    }

}

