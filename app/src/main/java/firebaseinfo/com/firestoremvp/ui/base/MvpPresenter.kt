package firebaseinfo.com.firestoremvp.ui.base

interface MvpPresenter<V : MvpView> {

    fun attachView(mvpView: V)

    fun detachView()

}