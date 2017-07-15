package ca.six.daily.utils

import ca.six.daily.core.BaseApp
import io.reactivex.Observable
import java.io.File

fun writeToCacheFile(content : String, fileName : String) {
    val dir = BaseApp.app.cacheDir //=> /data/user/0/ca.six.daily/cache
    val file = File(dir, fileName) //=> /data/user/0/ca.six.daily/cache/{fileName}
    file.writeText(content)
}

fun isCacheFileExist(fileName : String): Boolean {
    val dir = BaseApp.app.cacheDir //=> /data/user/0/ca.six.daily/cache
    val file = File(dir, fileName) //=> /data/user/0/ca.six.daily/cache/{fileName}
    return file.exists()
}

// TODO struggling. Should I handle the FileNotFoundException here?
fun readCacheFile(fileName : String): String {
    val dir = BaseApp.app.cacheDir //=> /data/user/0/ca.six.daily/cache
    val file = File(dir, fileName) //=> /data/user/0/ca.six.daily/cache/{fileName}
    return file.readText()
}

fun readCacheFileRx(fileName : String) : Observable<String> {
    return Observable.create<String> {
        if(isCacheFileExist(fileName)){
            println("szw get data from cache")
            val content = readCacheFile(fileName)
            it.onNext(content)
        } else {
            println("szw didn't get data from cache")
            it.onComplete()
        }
    }
}

