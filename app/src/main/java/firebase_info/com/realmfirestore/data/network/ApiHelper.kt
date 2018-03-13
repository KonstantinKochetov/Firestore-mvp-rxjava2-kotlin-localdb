package firebase_info.com.realmfirestore.data.network

import firebase_info.com.realmfirestore.data.model.realm.User
import io.reactivex.Flowable

/**
 * Created by konstantinkochetov on 11.03.18.
 */
interface ApiHelper {

    fun uploadUser(user: User): Flowable<User>
    fun getUsersFlowable(): Flowable<List<User>>
}