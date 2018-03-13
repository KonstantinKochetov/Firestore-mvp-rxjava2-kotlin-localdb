package firebase_info.com.realmfirestore.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import firebase_info.com.realmfirestore.mvp.MvpView

/**
 * Created by konstantinkochetov on 10.03.18.
 */

abstract class BaseActivity : AppCompatActivity(), MvpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }


   abstract fun inject()


}