package firebase_info.com.realmfirestore.data

import firebase_info.com.realmfirestore.data.local.DbHelperImpl
import firebase_info.com.realmfirestore.data.network.ApiHelperImpl
import firebase_info.com.realmfirestore.domain.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by konstantinkochetov on 11.03.18.
 */
@Singleton
open class DataManagerImpl @Inject constructor(
    private val apiHelper: ApiHelperImpl,
    private val dbHelperImpl: DbHelperImpl
) : DataManager {
    override fun createAndUploadUser(handler: AppCallback<User>): Disposable {
        return apiHelper.uploadUser(
            createTestUserWithSocialList()
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSubscriber<User>() {
                override fun onComplete() {
                    // do nothing
                }

                override fun onNext(t: User?) {
                    handler.onSuccess(t as User)
                    dbHelperImpl.insertUser(t)
                }

                override fun onError(e: Throwable?) {
                    if (e is Exception) {
                        handler.onFailure(e.message, e)
                    }
                }

            })
    }


    override fun getUserFromDatabase(handler: AppCallback<User>): Disposable {
        return dbHelperImpl.getUserFlowable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSubscriber<User>() {
                override fun onComplete() {
                    // do nothing
                }

                override fun onNext(t: User?) {
                    handler.onSuccess(t as User)
                }

                override fun onError(e: Throwable?) {
                    if (e is Exception) {
                        handler.onFailure(e.message, e)
                    }
                }

            })
    }

    override fun syncUsers(handler: AppCallback<List<User>>): Disposable {
        return apiHelper.getUsersFlowable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSubscriber<List<User>>() {
                override fun onComplete() {
                    // do nothing
                }

                override fun onNext(t: List<User>?) {
                    dbHelperImpl.saveUsers(t)
                    handler.onSuccess(t as List<User>)
                }

                override fun onError(e: Throwable?) {
                    if (e is Exception) {
                        handler.onFailure(e.message, e)
                    }
                }

            })

    }

    private fun createTestUserWithSocialList(): User {
        val user = User("test", "test2")
        return user
    }


}


