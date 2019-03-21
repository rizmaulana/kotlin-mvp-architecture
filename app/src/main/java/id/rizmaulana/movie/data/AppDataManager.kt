package id.rizmaulana.movie.data

import id.rizmaulana.movie.data.db.DbHelper
import id.rizmaulana.movie.data.model.api.MovieRequest
import id.rizmaulana.movie.data.model.db.MovieDto
import id.rizmaulana.movie.data.remote.ApiHelper
import javax.inject.Inject

/**
 * Created by Rizki Maulana on 08/03/19.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */
class AppDataManager
@Inject
constructor(val apiHelper: ApiHelper, val dbHelper: DbHelper) : DataManager {
    override fun getAllMovie() = dbHelper.getAllMovie()

    override fun clearMovie() = dbHelper.clearMovie()

    override fun addAll(movies: List<MovieDto>) = dbHelper.addAll(movies)

    override fun getMovie(request: MovieRequest, type: String) = apiHelper.getMovie(request, type)


}