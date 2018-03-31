package firebaseinfo.com.firestoremvp.data

import firebaseinfo.com.firestoremvp.data.model.firestore.User
import io.reactivex.disposables.Disposable

interface DataManager {

    fun addUser(handler: AppCallback<User>): Disposable

    fun getUserFromDatabase(handler: AppCallback<User>): Disposable

    fun syncUsersWithQuery(handler: AppCallback<List<User>>): Disposable

    fun getUserListFromDatabase(handler: AppCallback<List<User>>): Disposable

    fun syncUsersWithListener(handler: AppCallback<List<User>>): Disposable

    fun deleteAll(handler: AppCallback<String>): Disposable
}