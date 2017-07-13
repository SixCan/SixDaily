package ca.six.daily.biz.detail

/**
 * @copyright six.ca
 * Created by Xiaolin on 2017-07-10.
 */
interface IDailyDetailView {
    fun updateDetails(details : HashMap<String, String>)
    fun scrollToPosition(pos: Int)
}