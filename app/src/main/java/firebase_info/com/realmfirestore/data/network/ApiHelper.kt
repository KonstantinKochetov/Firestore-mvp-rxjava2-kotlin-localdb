package firebase_info.com.realmfirestore.data.network

import firebase_info.com.realmfirestore.data.model.realm.User
import io.reactivex.Flowable

interface ApiHelper {

    fun uploadUser(user: User): Flowable<User>

    fun getUsersFromServerWithQuery(): Flowable<List<User>>

    fun getUsersFromServerWithListener(): Flowable<List<User>>

    fun deleteEverythingFromServer(): Flowable<String>
}