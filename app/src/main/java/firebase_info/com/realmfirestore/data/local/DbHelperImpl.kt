package firebase_info.com.realmfirestore.data.local

import firebase_info.com.realmfirestore.data.room.AppDatabase
import firebase_info.com.realmfirestore.data.room.UserEntity
import firebase_info.com.realmfirestore.domain.User
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by konstantinkochetov on 11.03.18.
 */
@Singleton
open class DbHelperImpl @Inject constructor(
    val database: AppDatabase
) : DbHelper {

    override fun insertUser(user: User) {
        database.userDao().insertUser(UserEntity(0, user.firstName, user.lastName))
    }

    override fun getUserFlowable(): Flowable<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveUsers(t: List<User>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}