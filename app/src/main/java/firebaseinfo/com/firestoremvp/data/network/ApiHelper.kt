package firebaseinfo.com.firestoremvp.data.network

import firebaseinfo.com.firestoremvp.data.model.firestore.User
import io.reactivex.Flowable

interface ApiHelper {

    fun uploadUser(user: User): Flowable<User>

    fun getUsersFromServerWithQuery(): Flowable<List<User>>

    fun getUsersFromServerWithListener(): Flowable<List<User>>

    fun deleteEverythingFromServer(): Flowable<String>
}