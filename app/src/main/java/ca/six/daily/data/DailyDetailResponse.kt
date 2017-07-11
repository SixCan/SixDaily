package ca.six.daily.data

import org.json.JSONObject

/**
 * @copyright six.ca
 * Created by Xiaolin on 2017-07-10.
 */
class DailyDetailResponse(jsonStr : String) {
    var body : String
    var image : String
    var title : String

    init {
        val json = JSONObject(jsonStr)
        body = json.optString("body")
        image = json.optString("image")
        title = json.optString("title")
    }
}