package firebaseinfo.com.firestoremvp.data.local

import firebaseinfo.com.firestoremvp.data.model.firestore.User
import firebaseinfo.com.firestoremvp.data.model.realm.RealmUser
import io.reactivex.Flowable
import io.realm.Realm
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class DbHelperImpl @Inject constructor(val realm: Realm) : DbHelper {
    override fun insertOrUpdateUser(user: User) {
        realm.executeTransaction {
            realm.copyToRealmOrUpdate(user.transformForRealm())
        }
    }

    override fun getUserFlowable(): Flowable<User> {
        val user = realm.where(RealmUser::class.java).findFirst()
        return if (user != null) {
            Flowable.just(user.transformFromRealm())
        } else {
            Flowable.empty()
        }
    }

    override fun getUserListFlowable(): Flowable<List<User>> {
        val realmResult = realm.where(RealmUser::class.java).findAll()
        return if (realmResult != null) {
            val list = ArrayList<User>()
            realmResult.forEach { list.add(it.transformFromRealm()) }
            Flowable.just(list)
        } else {
            Flowable.empty()
        }
    }

    override fun syncUsersWithDatabase(users: List<User>?) {
        realm.executeTransaction {
            realm.where(RealmUser::class.java).findAll().deleteAllFromRealm()
            users?.forEach {
                realm.copyToRealmOrUpdate(it.transformForRealm())
            }
        }
    }

    override fun deleteAllFromDatabase() {
        realm.executeTransaction {
            realm.deleteAll()
        }
    }
}