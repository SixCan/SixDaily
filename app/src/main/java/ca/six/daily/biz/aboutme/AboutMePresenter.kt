package ca.six.daily.biz.aboutme

import ca.six.daily.core.network.HttpEngine
import ca.six.daily.data.CheckUpdateResponse

class AboutMePresenter {

    // todo : 1. use flatMap() to send a error and a successful ob
    // todo : 2. add the if/else chain in subscribe (x)
    fun checkForUpdate(){
        HttpEngine.request("version/android/2.5.1")
                .map{ jsonString -> CheckUpdateResponse(jsonString) }
                .subscribe{

                }
    }
}


// latest : {"status":0,"latest":"2.6.0"}

/*
// old
{
  "status": 1,
  "msg": "【更新】\r\n\r\n- 极大提升性能及稳定性\r\n- 部分用户无法使用新浪微博登录\r\n- 部分用户无图模式无法分享至微信及朋友圈",
  "url": "http://zhstatic.zhihu.com/pkg/store/daily/zhihu-daily-zhihu-2.6.0(744)-release.apk",
  "latest": "2.6.0"
}
*/

