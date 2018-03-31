package firebaseinfo.com.firestoremvp

import android.app.Application
import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import firebaseinfo.com.firestoremvp.di.components.AppComponent
import firebaseinfo.com.firestoremvp.di.components.DaggerAppComponent
import firebaseinfo.com.firestoremvp.di.modules.ContextModule
import io.realm.Realm
import javax.inject.Inject
import com.google.firebase.firestore.FirebaseFirestoreSettings

class MainApp : Application() {

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var firestore: FirebaseFirestore

    lateinit var component: AppComponent

    companion object {
        lateinit var instance: MainApp

        operator fun get(context: Context): MainApp {
            return context.applicationContext as MainApp
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
            .setPersistenceEnabled(false)
            .build()

        // Set Up Realm
        Realm.init(this)
    
    }
    
}
