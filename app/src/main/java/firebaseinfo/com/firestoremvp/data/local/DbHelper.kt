package firebaseinfo.com.firestoremvp.data.local

import firebaseinfo.com.firestoremvp.data.model.realm.User
import io.reactivex.Flowable

interface DbHelper {
    fun insertOrUpdateUser(user: User)

    fun getUserFlowable(): Flowable<User>

    fun getUserListFlowable(): Flowable<List<User>>

    fun syncUsersWithDatabase(users: List<User>?)

    fun deleteAllFromDatabase()
}