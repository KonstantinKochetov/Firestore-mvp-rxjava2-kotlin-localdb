package firebase_info.com.realmfirestore.ui.main

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

    override fun loadData() {
        compositeDisposable.add(
            dataManager.createAndUploadUser(object : AppCallback<User> {
                override fun onSuccess(response: User) {
                    mvpView?.display(response)

                }

                override fun onFailure(message: String?, e: Throwable) {
                    mvpView?.showError(message)
                }
            })
        )
    }

    override fun getData() {
        compositeDisposable.add(
            dataManager.getUserFromDatabase(object : AppCallback<User> {
                override fun onSuccess(response: User) {
                    mvpView?.display(response)
                }

                override fun onFailure(message: String?, e: Throwable) {
                    mvpView?.showError(message)
                }
            })
        )
    }

    fun sync() {
        compositeDisposable.add(
            dataManager.syncUsers(object : AppCallback<List<User>> {
                override fun onSuccess(response: List<User>) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onFailure(message: String?, e: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            })
        )
    }

    override fun detachView() {
        super.detachView()
        compositeDisposable.clear()
    }

}