package firebase_info.com.realmfirestore.mvp

interface Presenter<V : MvpView> {

    fun attachView(mvpView: V)

    fun detachView()

}