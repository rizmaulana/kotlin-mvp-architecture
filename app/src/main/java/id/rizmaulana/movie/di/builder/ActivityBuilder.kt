package id.rizmaulana.movie.di.builder


import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.rizmaulana.movie.ui.movie.MovieActivity
import id.rizmaulana.movie.ui.movie.MovieModule

/**
 * Created by Rizki Maulana on 08/03/19.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(MovieModule::class))
    internal abstract fun bindMovieActivity(): MovieActivity




}
