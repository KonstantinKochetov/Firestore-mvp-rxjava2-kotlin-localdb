package firebase_info.com.realmfirestore.mvp

/**
 * Created by konstantinkochetov on 10.03.18.
 */
interface Presenter<V : MvpView> {

    fun attachView(mvpView: V)

    fun detachView()

}