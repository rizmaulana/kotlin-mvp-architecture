package id.rizmaulana.movie.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import id.rizmaulana.movie.R
import id.rizmaulana.movie.data.AppDataManager
import id.rizmaulana.movie.data.DataManager
import id.rizmaulana.movie.data.remote.ApiEndPoint
import id.rizmaulana.movie.data.remote.ApiHelper
import id.rizmaulana.movie.data.remote.AppApiHelper
import id.rizmaulana.movie.data.remote.ServiceApi
import id.rizmaulana.movie.utils.AppSchedulerProvider
import id.rizmaulana.movie.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Singleton

/**
 * Created by Rizki Maulana on 10/03/19.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */
@Module
class ApplicationTestModule(val application: Application){

    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper {
        return appApiHelper
    }

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    internal fun provideCalligraphyDefaultConfig(): CalligraphyConfig {
        return CalligraphyConfig.Builder()
            .setDefaultFontPath("fonts/Roboto-Regular.ttf")
            .setFontAttrId(R.attr.fontPath)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(ApiEndPoint.ForecastApi)
        .build()

    @Provides
    @Singleton
    internal fun provideServiceApi(retrofit: Retrofit) =
        retrofit.create(ServiceApi::class.java)

    @Provides
    internal fun provideCompositeDisposable() = CompositeDisposable()

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }


}