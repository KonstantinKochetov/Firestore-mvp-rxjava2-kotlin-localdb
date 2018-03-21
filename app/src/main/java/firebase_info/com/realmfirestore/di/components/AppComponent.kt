package firebase_info.com.realmfirestore.di.components

import dagger.Component
import firebase_info.com.realmfirestore.RealmFirestoreApp
import firebase_info.com.realmfirestore.ui.base.BaseActivity
import firebase_info.com.realmfirestore.di.modules.ContextModule
import firebase_info.com.realmfirestore.di.modules.FirebaseModule
import firebase_info.com.realmfirestore.di.modules.RealmHelperModule
import firebase_info.com.realmfirestore.di.modules.RxModule
import firebase_info.com.realmfirestore.data.DataManagerImpl
import firebase_info.com.realmfirestore.data.local.DbHelperImpl
import firebase_info.com.realmfirestore.data.network.ApiHelperImpl
import firebase_info.com.realmfirestore.ui.main.MainActivity
import javax.inject.Singleton

/**
 * Created by frs on 16.04.17.
 * Provides all Singletons used by the App
 */
@Singleton
@Component(modules = [(ContextModule::class), (RxModule::class), (RealmHelperModule::class), (FirebaseModule::class)])
interface AppComponent {

    fun inject(application: RealmFirestoreApp)

    fun inject(activity: MainActivity)

    fun inject(activity: BaseActivity)

    var dataSourceImpl: DataManagerImpl

    var apiHelperImpl: ApiHelperImpl

    var dbHelperImpl: DbHelperImpl

}
