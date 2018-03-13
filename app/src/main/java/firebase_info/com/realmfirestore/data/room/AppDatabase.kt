package firebase_info.com.realmfirestore.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by konstantinkochetov on 13.03.18.
 */
@Database(
    entities = arrayOf(UserEntity::class),
    version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): RoomUserDao

    companion object {
        fun buildAppDatabase(context: Context): AppDatabase = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            RoomContract.DATABASE_APP
        ).build()

    }

}