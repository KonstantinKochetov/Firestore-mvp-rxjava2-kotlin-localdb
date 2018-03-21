package firebase_info.com.realmfirestore.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class RxModule() {

    /**
     * Allow the application context to be injected but require that it be annotated with [ ] to explicitly differentiate it from an activity context.
     */
    @Provides
    @Singleton
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

}
