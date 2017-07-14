package ca.six.daily.utils

import ca.six.daily.core.BaseApp
import java.io.File

fun writeToCacheFile(content : String, fileName : String) {
    val dir = BaseApp.app.cacheDir //=> /data/user/0/ca.six.daily/cache
    val file = File(dir, fileName) //=> /data/user/0/ca.six.daily/cache/{fileName}
    file.writeText(content)
}