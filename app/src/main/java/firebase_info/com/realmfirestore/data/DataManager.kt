package firebase_info.com.realmfirestore.data

import firebase_info.com.realmfirestore.data.model.realm.User
import io.reactivex.disposables.Disposable
import rx.Subscription

/**
 * Created by konstantinkochetov on 11.03.18.
 */
interface DataManager {

    fun createAndUploadUser(handler: AppCallback<User>): Disposable

    fun getUserFromDatabase(handler: AppCallback<User>): Disposable

    fun syncUsers(handler: AppCallback<List<User>>): Disposable
}