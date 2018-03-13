package firebase_info.com.realmfirestore.dagger.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import firebase_info.com.realmfirestore.data.room.AppDatabase
import javax.inject.Singleton

/**
 * Created by konstantinkochetov on 10.03.18.
 */
@Module
class RoomModule {

    @Provides @Singleton fun provideRoomDataSource(context: Context) =
        AppDatabase.buildAppDatabase(context)
}