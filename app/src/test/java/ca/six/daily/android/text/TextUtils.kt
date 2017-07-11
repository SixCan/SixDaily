package ca.six.daily.android.text

class TextUtils {
    fun isEmpty(str: CharSequence?): Boolean {
        if (str == null || str.length == 0)
            return true
        else
            return false
    }
}