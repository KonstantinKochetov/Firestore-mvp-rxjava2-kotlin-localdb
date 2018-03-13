package firebase_info.com.realmfirestore.ui.main

import firebase_info.com.realmfirestore.domain.User
import firebase_info.com.realmfirestore.mvp.MvpView

/**
 * Created by konstantinkochetov on 10.03.18.
 */
class MainContract {
    interface View : MvpView {
        fun initializeViews()
        fun display(user: User)
        fun showError(message: String?)
    }

    interface Presenter {
        fun initializeViews()
        fun loadData()
        fun getData()
    }
}