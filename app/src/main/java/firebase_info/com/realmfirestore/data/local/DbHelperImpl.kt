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

    override fun getUserFlowable() : Flowable<User> {
        val user = realm.where(RealmUser::class.java).equalTo("userID", "testUserId").findFirst()
        return if (user != null) {
            Flowable.just(user.transformFromRealm())
        } else {
            Flowable.empty()
        }
    }

    override fun saveUsers(t: List<User>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}