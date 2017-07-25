package ca.six.daily.biz.aboutme

import ca.six.daily.core.network.HttpEngine
import ca.six.daily.data.CheckUpdateResponse

class AboutMePresenter {

    fun checkForUpdate(){
        HttpEngine.request("version/android/2.5.4")
                .map{ jsonString -> CheckUpdateResponse(jsonString) }
                .subscribe{
                    println("szw ${it.latest} : ${it.message}")
                }
    }
}
