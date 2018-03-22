package firebaseinfo.com.firestoremvp.ui.main

import android.support.annotation.StringRes
import firebaseinfo.com.firestoremvp.data.model.realm.User
import firebaseinfo.com.firestoremvp.ui.base.MvpView

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
        fun getUserFromDatabase()
        fun getUserListFromDatabase()
        fun syncUsersWithQuery()
        fun syncUsersWithListener()
        fun deleteAll()
    }
}