package ca.six.daily.biz.detail

import ca.six.daily.core.network.HttpEngine
import ca.six.daily.data.DailyDetailResponse

/**
 * @copyright six.ca
 * Created by Xiaolin on 2017-07-10.
 */
class DailyDetailPresenter(val view: IDailyDetailView) {

    fun getDetails(id: Long) {
        val details = hashMapOf<String, String>()

        HttpEngine.request("news/" + id)
                .map { DailyDetailResponse(it) }
                .map {
                    details.put("title", it.title)
                    details.put("body", it.body)
                    details.put("image", it.image)
                    details.put("id", it.id.toString())
                    details
                }
                .subscribe {
                    println("DailyDetailPresenter-subscribe")
                    view.updateDetails(details)
                }
    }
}