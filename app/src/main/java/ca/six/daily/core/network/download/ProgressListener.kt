package ca.six.daily.core.network.download

interface ProgressListener {
    fun update(bytesRead : Long, contentLength : Long, isDone : Boolean)
}