package firebase_info.com.realmfirestore.data.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable

/**
 * Created by konstantinkochetov on 13.03.18.
 */
@Dao
interface RoomUserDao {

    @Insert
    fun insertUser(user: UserEntity)

    @Query(RoomContract.SELECT_CURRENCIES)
    fun getUser(): Flowable<UserEntity>

}