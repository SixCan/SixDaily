package ca.six.daily.biz.detail

import ca.six.daily.core.network.HttpEngine
import ca.six.daily.data.DailyDetailResponse
import ca.six.daily.utils.readCachedDetails
import ca.six.daily.utils.writeToCacheFile
import io.reactivex.Observable

/**
 * @copyright six.ca
 * Created by Xiaolin on 2017-07-10.
 */
class DailyDetailPresenter(val view: IDailyDetailView) {

    fun getDetails(id: Long) {
        val details = hashMapOf<String, String>()

        val cachedObservable = readCachedDetails(id)
                .map { it }
        val networkObservable = HttpEngine.request("news/$id")
                .map { jsonResp ->
                    writeToCacheFile(jsonResp, "news_$id.json")
                    jsonResp
                }

        Observable.concat(cachedObservable, networkObservable)
                .map {
                    DailyDetailResponse(it)
                }
                .map {
                    details.put("title", it.title)
                    details.put("body", it.body)
                    details.put("image", it.image)
                    details.put("id", it.id.toString())
                    details.put("cssVer", it.cssVer)
                    details
                }
                .subscribe {
                    println("DailyDetailPresenter-subscribe")
                    view.updateDetails(details)
                }
    }
}