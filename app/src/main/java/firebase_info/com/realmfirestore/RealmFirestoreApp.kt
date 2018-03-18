package firebase_info.com.realmfirestore

import android.app.Application
import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import firebase_info.com.realmfirestore.dagger.components.AppComponent
import firebase_info.com.realmfirestore.dagger.components.DaggerAppComponent
import firebase_info.com.realmfirestore.dagger.modules.ContextModule
import io.realm.Realm
import javax.inject.Inject
import com.google.firebase.firestore.FirebaseFirestoreSettings

class RealmFirestoreApp : Application() {

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var firestore: FirebaseFirestore

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

        // Set Up Firebase
        FirebaseApp.initializeApp(context)
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()

        // Set Up Realm
        Realm.init(this)
    
    }
    
}
