package firebase_info.com.realmfirestore.data.local

import firebase_info.com.realmfirestore.domain.User
import io.reactivex.Flowable

/**
 * Created by konstantinkochetov on 11.03.18.
 */
interface DbHelper {
    fun insertUser(user: User)
    fun getUserFlowable() : Flowable<User>
    fun saveUsers(t: List<User>?)
}