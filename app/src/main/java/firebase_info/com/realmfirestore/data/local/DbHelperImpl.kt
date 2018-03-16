package firebase_info.com.realmfirestore.data.local

import firebase_info.com.realmfirestore.data.model.realm.RealmUser
import firebase_info.com.realmfirestore.data.model.realm.User
import io.reactivex.Flowable
import io.realm.Realm
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by konstantinkochetov on 11.03.18.
 */
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
}