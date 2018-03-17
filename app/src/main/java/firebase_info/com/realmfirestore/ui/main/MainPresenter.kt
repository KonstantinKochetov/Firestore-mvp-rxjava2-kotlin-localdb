package firebase_info.com.realmfirestore.ui.main

import firebase_info.com.realmfirestore.R
import firebase_info.com.realmfirestore.base.BasePresenter
import firebase_info.com.realmfirestore.data.AppCallback
import firebase_info.com.realmfirestore.data.DataManagerImpl
import firebase_info.com.realmfirestore.data.model.realm.User
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class MainPresenter @Inject constructor(private val dataManager: DataManagerImpl) :
    BasePresenter<MainContract.View>(),
    MainContract.Presenter {
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun initializeViews() {
        mvpView?.initializeViews()
    }

    override fun addUser() {
        compositeDisposable.add(
            dataManager.addUser(object : AppCallback<User> {
                override fun onSuccess(response: User) {
                    mvpView?.showMessage(R.string.s_user_added)
                    mvpView?.displayUser(response)

                }

                override fun onFailure(message: String?, e: Throwable) {
                    mvpView?.showError(message)
                }
            })
        )
    }

    override fun getUserFromDb() {
        compositeDisposable.add(
            dataManager.getUserFromDatabase(object : AppCallback<User> {
                override fun onSuccess(response: User) {
                    mvpView?.showMessage(R.string.s_user_extracted_db)
                    mvpView?.displayUser(response)
                }

                override fun onFailure(message: String?, e: Throwable) {
                    mvpView?.showError(message)
                }
            })
        )
    }

    override fun getUserListFromDb() {
        compositeDisposable.add(
            dataManager.getUserListFromDatabase(object : AppCallback<List<User>> {
                override fun onSuccess(response: List<User>) {
                    mvpView?.showMessage(R.string.s_users_extracted_db)
                    mvpView?.displayUserList(response)
                }

                override fun onFailure(message: String?, e: Throwable) {
                    mvpView?.showError(message)
                }

            })
        )
    }

    override fun syncUsersWithQuery() {
        compositeDisposable.add(
            dataManager.syncUsersWithQuery(object : AppCallback<List<User>> {
                override fun onSuccess(response: List<User>) {
                    mvpView?.displayUserList(response)
                    mvpView?.showMessage(R.string.s_sync_users_query_message)
                }

                override fun onFailure(message: String?, e: Throwable) {
                    mvpView?.showError(message)
                }

            })
        )
    }

    override fun syncUsersWithListener() {
        compositeDisposable.add(
            dataManager.syncUsersWithListener(object : AppCallback<List<User>> {
                override fun onSuccess(response: List<User>) {
                    mvpView?.displayUserList(response)
                    mvpView?.showMessage(R.string.s_sync_users_listener_message)
                }

                override fun onFailure(message: String?, e: Throwable) {
                    mvpView?.showError(message)
                }

            })
        )
    }

    override fun deleteAll() {
        compositeDisposable.add(
            dataManager.deleteAll(object : AppCallback<String> {
                override fun onSuccess(response: String) {
                    mvpView?.cleanTextView()
                    mvpView?.showMessage(R.string.s_everything_is_deleted)
                }

                override fun onFailure(message: String?, e: Throwable) {
                    mvpView?.showError(message)
                }

            })
        )
    }

    override fun detachView() {
        super.detachView()
        compositeDisposable.clear()
    }

}