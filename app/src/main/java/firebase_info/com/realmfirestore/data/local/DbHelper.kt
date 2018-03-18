package firebase_info.com.realmfirestore.data.local

import firebase_info.com.realmfirestore.data.model.realm.User
import io.reactivex.Flowable

interface DbHelper {
    fun insertOrUpdateUser(user: User)

    fun getUserFlowable(): Flowable<User>

    fun getUserListFlowable(): Flowable<List<User>>

    fun syncUsersWithDatabase(users: List<User>?)

    fun deleteAllFromDatabase()
}