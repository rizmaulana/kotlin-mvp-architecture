package id.rizmaulana.movie

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import id.rizmaulana.movie.di.component.DaggerAppComponent
import io.objectbox.BoxStore
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Inject


/**
 * Created by Rizki Maulana on 15/11/18.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */
class AppController : Application(), HasActivityInjector {
    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector

    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    internal lateinit var mCalligraphyConfig: CalligraphyConfig

    @Inject
    internal  lateinit var boxStore: BoxStore

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
        CalligraphyConfig.initDefault(mCalligraphyConfig)

    }

}