package firebase_info.com.realmfirestore

import android.app.Application
import android.content.Context
import com.google.firebase.FirebaseApp
import firebase_info.com.realmfirestore.dagger.components.AppComponent
import firebase_info.com.realmfirestore.dagger.components.DaggerAppComponent
import firebase_info.com.realmfirestore.dagger.modules.ContextModule
import io.realm.Realm
import javax.inject.Inject

/**
 * Created by konstantinkochetov on 10.03.18.
 */
class RealmFirestoreApp : Application() {

    @Inject
    lateinit var context: Context

    lateinit var component: AppComponent

    companion object {
        lateinit var instance: RealmFirestoreApp

        operator fun get(context: Context): RealmFirestoreApp {
            return context.applicationContext as RealmFirestoreApp
        }
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
        component = DaggerAppComponent.builder().contextModule(ContextModule(this)).build()
        component.inject(this)

        FirebaseApp.initializeApp(context)
        // TODO disable firebase persistence

        // Set Up Realm
        Realm.init(this)
    
    }
    
}
