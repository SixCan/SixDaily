package ca.six.daily.biz.home

import ca.six.daily.view.ViewType

interface IDailyListView {
    fun refresh(data: MutableList<ViewType>)
    fun jumpToDetilsPage(thisStoryID: Long, allIDs: Array<Long>)
}

