package firebase_info.com.realmfirestore.ui.base

interface Presenter<V : MvpView> {

    fun attachView(mvpView: V)

    fun detachView()

}