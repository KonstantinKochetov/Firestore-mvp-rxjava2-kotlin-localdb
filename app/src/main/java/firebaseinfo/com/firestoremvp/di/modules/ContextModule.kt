package firebaseinfo.com.firestoremvp.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import firebaseinfo.com.firestoremvp.MainApp
import javax.inject.Singleton

@Module
class ContextModule(private val application: MainApp) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return application
    }

}
