package firebase_info.com.realmfirestore.data.local

import firebase_info.com.realmfirestore.data.model.realm.User
import io.reactivex.Flowable

/**
 * Created by konstantinkochetov on 11.03.18.
 */
interface DbHelper {
    fun insertOrUpdateUser(user: User)
    fun getUserFlowable() : Flowable<User>
    fun getUserListFlowable(): Flowable<List<User>>
    fun syncUsersWithDatabase(users: List<User>?)
}