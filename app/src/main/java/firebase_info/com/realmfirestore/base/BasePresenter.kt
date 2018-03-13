package firebase_info.com.realmfirestore.base

import firebase_info.com.realmfirestore.mvp.MvpView
import firebase_info.com.realmfirestore.mvp.Presenter
import javax.inject.Inject

/**
 * Created by konstantinkochetov on 10.03.18.
 */
open class BasePresenter<T : MvpView> @Inject constructor():
    Presenter<T> {

    var mvpView: T? = null
        private set

    val isViewAttached: Boolean
        get() = mvpView != null

    override fun attachView(mvpView: T) {
        this.mvpView = mvpView
    }

    override fun detachView() {
        mvpView = null
    }

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    class MvpViewNotAttachedException :
        RuntimeException("Please call Presenter.attachView(MvpView) before" + " requesting data to the Presenter")
}
