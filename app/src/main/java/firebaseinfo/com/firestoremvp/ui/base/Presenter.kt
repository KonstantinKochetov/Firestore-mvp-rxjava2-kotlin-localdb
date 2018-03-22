package firebaseinfo.com.firestoremvp.ui.base

interface Presenter<V : MvpView> {

    fun attachView(mvpView: V)

    fun detachView()

}