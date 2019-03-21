package id.rizmaulana.movie.ui.movie

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import id.rizmaulana.movie.data.DataManager
import id.rizmaulana.movie.ui.common.MovieAdapter
import id.rizmaulana.movie.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Rizki Maulana on 19/03/19.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */
@Module
class MovieModule{

    @Provides
    fun providePresenter(cd: CompositeDisposable, dm: DataManager, schduler: SchedulerProvider): MoviePresenter {
        return MoviePresenterImpl(cd, dm, schduler)
    }

    @Provides
    fun provideLayoutManager(context: Context)  = LinearLayoutManager(context)

    @Provides
    fun providesMovieAdapter() = MovieAdapter()
}