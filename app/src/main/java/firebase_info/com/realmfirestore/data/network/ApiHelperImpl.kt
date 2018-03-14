package firebase_info.com.realmfirestore.data.network

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import firebase_info.com.realmfirestore.data.room.UserEntity
import firebase_info.com.realmfirestore.domain.User
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
open class ApiHelperImpl @Inject constructor(
    val firestore: FirebaseFirestore
) : ApiHelper {
    val TAG = "ApiHelperImpl"

    override fun uploadUser(user: User): Flowable<User> {
        return Flowable.create({ subscriber: FlowableEmitter<User> ->
            firestore.collection("users")
                .add(user)
                .addOnSuccessListener({ documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.id)
                    documentReference.get().addOnCompleteListener({ task ->
                        val user = task.result.toObject(User::class.java)
                        subscriber.onNext(user)
                    })
                })
                .addOnFailureListener({ e ->
                    Log.w(TAG, "Error adding document", e)
                    subscriber.onError(e)
                })
        }, BackpressureStrategy.BUFFER)

    }

    override fun getUsersFlowable(): Flowable<List<User>> {
        return Flowable.create({ subscriber: FlowableEmitter<List<User>> ->
            firestore.collection("users")
                .get()
                .addOnSuccessListener({ documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.documents)
                    val list = mutableListOf<User>()
                    documentReference.documents.map {
                        list.add(it.toObject(User::class.java))
                    }
                    subscriber.onNext(list)
                })
                .addOnFailureListener({ e ->
                    Log.w(TAG, "Error adding document", e)
                    subscriber.onError(e)
                })
        }, BackpressureStrategy.BUFFER)

    }
}
