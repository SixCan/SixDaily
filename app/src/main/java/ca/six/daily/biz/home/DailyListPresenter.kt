package ca.six.daily.biz.home

import ca.six.daily.biz.home.viewmodel.ListItemViewModel
import ca.six.daily.biz.home.viewmodel.ListTitleViewModel
import ca.six.daily.core.network.HttpEngine
import ca.six.daily.data.DailyListResponse
import ca.six.daily.data.Story
import ca.six.daily.utils.writeToCacheFile
import ca.six.daily.view.ViewType

class DailyListPresenter(val view: IDailyListView) {
    lateinit var listData : DailyListResponse
    lateinit var viewModels : MutableList<ViewType<out Any>>
    var ids : MutableList<Long> = ArrayList()

    fun requestData() {
        viewModels = ArrayList()

        HttpEngine.request("news/latest")
                .map{ jsonString ->
                    writeToCacheFile(jsonString, "news_latest.json")
                    jsonString
                }

                .map { jsonString ->
                    listData = DailyListResponse(jsonString)
                    listData
                }
                .map { resp ->
                    viewModels.add(ListTitleViewModel(resp.date))
                    resp.stories.forEach { story ->
                        viewModels.add(ListItemViewModel(story))
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
                .subscribe { view.refresh(viewModels) }

    }

    fun jumpToDetail(position: Int) {
        val viewModel = viewModels[position]
        if(viewModel.getData() is Story){
            val story = viewModel.getData() as Story
            val idArray : LongArray = ids.toLongArray()
            view.jumpToDetilsPage(story.id, idArray)
        }

    }
}
