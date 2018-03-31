package firebaseinfo.com.firestoremvp.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class RxModule {

    @Provides
    @Singleton
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

}
