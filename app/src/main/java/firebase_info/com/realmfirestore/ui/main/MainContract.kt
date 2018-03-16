package firebase_info.com.realmfirestore.ui.main

import firebase_info.com.realmfirestore.data.model.realm.User
import firebase_info.com.realmfirestore.mvp.MvpView

/**
 * Created by konstantinkochetov on 10.03.18.
 */
class MainContract {
    interface View : MvpView {
        fun initializeViews()
        fun displayUser(user: User)
        fun displayUserList(users: List<User>)
        fun showSyncSuccess(message: String?)
        fun showError(message: String?)
    }

    interface Presenter {
        fun initializeViews()
        fun addUser()
        fun getUserFromDb()
        fun getUserListFromDb()
        fun syncUsersWithQuery()
        fun syncUsersWithListener()
    }
}