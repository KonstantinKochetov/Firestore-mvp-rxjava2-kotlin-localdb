package firebaseinfo.com.firestoremvp.di.modules

import dagger.Module
import dagger.Provides
import firebaseinfo.com.firestoremvp.data.local.AppRealmModule
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
class RealmHelperModule {

    @Provides
    @Singleton
    fun providesRealm(realmConfiguration: RealmConfiguration): Realm {
        return Realm.getInstance(realmConfiguration)
    }

    @Provides
    @Singleton
    fun providesRealmConfiguration(): RealmConfiguration {
            return RealmConfiguration.Builder()
                .modules(AppRealmModule())
                .name("realmfirestore.realm")
                .schemaVersion(1)
                .build()
    }
}