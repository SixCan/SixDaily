package ca.six.daily.biz.aboutme

import android.os.Bundle
import ca.six.daily.core.BaseActivity

class AboutMeActivity : BaseActivity() {
    val presenter : AboutMePresenter by lazy { AboutMePresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // check for update : version/android/2.5.4
        presenter.checkForUpdate()
    }
}
