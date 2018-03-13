package firebase_info.com.realmfirestore.data.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by konstantinkochetov on 11.03.18.
 */
@Entity(tableName = RoomContract.TABLE_USERS)
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    var firstName: String,
    var lastName: String
)
