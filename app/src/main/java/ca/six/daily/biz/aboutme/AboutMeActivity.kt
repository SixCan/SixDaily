package ca.six.daily.biz.aboutme

import android.os.Bundle
import ca.six.daily.core.BaseActivity
import ca.six.daily.R

class AboutMeActivity : BaseActivity(), IAboutMeView {
    val presenter : AboutMePresenter by lazy { AboutMePresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_me)

        // check for update : version/android/2.5.4
        presenter.checkForUpdate()
    }

    override fun showUpdateMessage() {

    }
}
