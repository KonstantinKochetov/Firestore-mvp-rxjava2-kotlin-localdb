package firebaseinfo.com.firestoremvp.data.network

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import firebaseinfo.com.firestoremvp.data.model.realm.User
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

    var registration : ListenerRegistration? = null

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

    override fun getUsersFromServerWithQuery(): Flowable<List<User>> {
        return Flowable.create({ subscriber: FlowableEmitter<List<User>> ->
            firestore.collection("users")
                .get()
                .addOnSuccessListener({ documentReference ->
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

    override fun getUsersFromServerWithListener(): Flowable<List<User>> {
        registration?.remove()
        return Flowable.create({ subscriber: FlowableEmitter<List<User>> ->
            registration = firestore.collection("users")
                .addSnapshotListener { snapshots, exception ->
                    if (exception != null) {
                        subscriber.onError(exception)
                    }
                    val users = ArrayList<User>()
                    snapshots.forEach {
                        users.add(it.toObject(User::class.java))
                    }
                    subscriber.onNext(users)
                }
        }, BackpressureStrategy.BUFFER)
    }

    override fun deleteEverythingFromServer(): Flowable<String> {
        return Flowable.create({ subscriber: FlowableEmitter<String> ->
            firestore.collection("users")
                .get()
                .addOnSuccessListener({ documentReference ->
                    documentReference.documents.map {
                        it.reference.delete()
                    }
                    subscriber.onComplete()
                })
                .addOnFailureListener({ e ->
                    Log.w(TAG, "Error adding document", e)
                    subscriber.onError(e)
                })
        }, BackpressureStrategy.BUFFER)

    }

}
