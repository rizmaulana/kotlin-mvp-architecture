package id.rizmaulana.movie

import id.rizmaulana.movie.data.AppDataManager
import id.rizmaulana.movie.data.DataManager
import id.rizmaulana.movie.data.apimodel.ForecastResponse
import id.rizmaulana.movie.data.remote.ApiEndPoint
import id.rizmaulana.movie.data.remote.ApiHelper
import id.rizmaulana.movie.data.remote.AppApiHelper
import id.rizmaulana.movie.data.remote.ServiceApi
import id.rizmaulana.movie.utils.TestSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.TestScheduler
import junit.framework.Assert.assertEquals
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


/**
 * Created by Rizki Maulana on 09/03/19.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */
@RunWith(MockitoJUnitRunner::class)
class WeatherPresenterImplTest {
    @Mock
    lateinit var view: WeatherView
    @Mock
    lateinit var presenter: WeatherPresenter
    @Mock
    lateinit var apiHelper: ApiHelper

    lateinit var dataManager: DataManager
    lateinit var serviceApi: ServiceApi
    lateinit var server: MockWebServer
    lateinit var compositeDisposable: CompositeDisposable
    lateinit var testSchedulerProvide: TestSchedulerProvider
    lateinit var testScheduler: TestScheduler


    @Before
    @Throws
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        serviceApi = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(ApiEndPoint.ForecastApi)
            .client(OkHttpClient())
            .build().create(ServiceApi::class.java)

        testScheduler = TestScheduler()
        apiHelper = AppApiHelper(serviceApi)
        compositeDisposable = CompositeDisposable()
        testSchedulerProvide = TestSchedulerProvider(testScheduler)
        dataManager = AppDataManager(apiHelper)


        presenter = WeatherPresenterImpl(compositeDisposable, dataManager, testSchedulerProvide)
        presenter.setView(view)

        server = MockWebServer()
        server.start()
    }


    @Test
    fun loadForecastSuccess() {
        val testObserver = TestObserver<ForecastResponse>()
        val mockResponse = MockResponse().setBody(getJson("response_success.json"))

        server.enqueue(mockResponse)
        server.url(ApiEndPoint.Forecast)


        presenter.loadForecast("", "")
        testObserver.awaitTerminalEvent(2, TimeUnit.SECONDS)
        testObserver.assertNoErrors()

        testScheduler.triggerActions()


        val serverRequest = server.takeRequest()
        assertEquals(ApiEndPoint.Forecast, serverRequest.path)
        assertEquals(getJson("response_success.json"), serverRequest.body.readUtf8());
    }

    @Test
    fun loadForecastError() {

    }

    @Test
    fun loadForecastTimeout() {

    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        presenter.dispose()
        server.shutdown()
    }

    fun getJson(path: String): String {
        val uri = this.javaClass.classLoader.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }
}