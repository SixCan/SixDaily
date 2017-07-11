package ca.six.daily.biz.detail

import ca.six.daily.core.network.HttpEngine
import ca.six.daily.data.DailyDetailResponse

/**
 * @copyright six.ca
 * Created by Xiaolin on 2017-07-10.
 */
class DailyDetailPresenter(val view: IDailyDetailView) {

    fun getDetails(id: String) {
        val details = hashMapOf<String, String>()
        HttpEngine.request("news/" + id)
                .map { DailyDetailResponse(it) }
                .map {
                    details.put("title", "")
                    details.put("body", "")
                    details
                }
                .subscribe {
                    view.updateDetails(details)
                }
    }
}