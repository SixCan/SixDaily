package ca.six.daily.data

import org.json.JSONObject

/**
 * @copyright six.ca
 * Created by Xiaolin on 2017-07-10.
 */
class DailyDetailResponse(jsonStr: String) {
    var body: String
    var image: String
    var title: String
    var id: Long
    var cssVer: String

    val REDUNDANT_PART = "<div class=\"img-place-holder\"></div>\n\n\n\n"

    init {
        val json = JSONObject(jsonStr)
        body = json.optString("body").replace(REDUNDANT_PART, "")
        image = json.optString("image")
        title = json.optString("title")
        id = json.optLong("id", 0)
        val css = json.optJSONArray("css")[0] as String
        cssVer = if(css.isNotEmpty()) css.substring(css.indexOf("=") + 1, css.length) else ""
    }
}