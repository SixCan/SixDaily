package ca.six.daily.core.network.download

import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.*
import okio.Okio
import okio.BufferedSource



class ProgressResponseBody(val responseBody: ResponseBody, val progressListener: ProgressListener) : ResponseBody() {
    var bufferedSource : BufferedSource? = null

    override fun contentLength(): Long {
        return responseBody.contentLength()
    }

    override fun contentType(): MediaType? {
        return responseBody.contentType()
    }

    override fun source(): BufferedSource? {
        if(bufferedSource == null){
            val sourceInResponse = responseBody.source()
            var forwardingSource = object : ForwardingSource(sourceInResponse) {
                var totalBytesRead = 0L
                override fun read(sink: Buffer, byteCount: Long): Long {
                    var bytesRead = super.read(sink, byteCount)
                    var isDone = false
                    if(bytesRead == -1L){
                        bytesRead = 0
                        isDone = true
                    }
                    totalBytesRead += bytesRead
                    progressListener.update(totalBytesRead, contentLength(), isDone)
                    return bytesRead
                }
            }
            bufferedSource = Okio.buffer(forwardingSource)
        }
        return bufferedSource
    }



}
