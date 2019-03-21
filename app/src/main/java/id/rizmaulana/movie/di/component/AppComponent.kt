package id.rizmaulana.movie.di.component


import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import id.rizmaulana.movie.AppController
import id.rizmaulana.movie.data.DataManager
import id.rizmaulana.movie.di.builder.ActivityBuilder
import id.rizmaulana.movie.di.module.AppModule
import javax.inject.Singleton

/**
 * Created by Rizki Maulana on 08/03/19.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */


@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class))
interface AppComponent {

    fun inject(app: AppController)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    abstract fun application(): Application

    abstract fun getDataManager(): DataManager
}
