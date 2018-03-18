package firebase_info.com.realmfirestore.ui.main

import android.support.annotation.StringRes
import firebase_info.com.realmfirestore.data.model.realm.User
import firebase_info.com.realmfirestore.mvp.MvpView

class MainContract {
    interface View : MvpView {
        fun initializeViews()
        fun displayUser(user: User)
        fun displayUserList(users: List<User>)
        fun showError(message: String?)
        fun cleanTextView()
        fun showMessage(@StringRes message: Int?)
    }

    interface Presenter {
        fun initializeViews()
        fun addUser()
        fun getUserFromDb()
        fun getUserListFromDb()
        fun syncUsersWithQuery()
        fun syncUsersWithListener()
        fun deleteAll()
    }
}