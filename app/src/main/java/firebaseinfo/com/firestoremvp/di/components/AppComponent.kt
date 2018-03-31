package firebaseinfo.com.firestoremvp.di.components

import dagger.Component
import firebaseinfo.com.firestoremvp.MainApp
import firebaseinfo.com.firestoremvp.ui.base.BaseActivity
import firebaseinfo.com.firestoremvp.di.modules.ContextModule
import firebaseinfo.com.firestoremvp.di.modules.FirebaseModule
import firebaseinfo.com.firestoremvp.di.modules.RealmHelperModule
import firebaseinfo.com.firestoremvp.di.modules.RxModule
import firebaseinfo.com.firestoremvp.data.DataManagerImpl
import firebaseinfo.com.firestoremvp.data.local.DbHelperImpl
import firebaseinfo.com.firestoremvp.data.network.ApiHelperImpl
import firebaseinfo.com.firestoremvp.ui.main.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [(ContextModule::class), (RxModule::class), (RealmHelperModule::class), (FirebaseModule::class)])
interface AppComponent {

    fun inject(application: MainApp)

    fun inject(activity: MainActivity)

    fun inject(activity: BaseActivity)

}
