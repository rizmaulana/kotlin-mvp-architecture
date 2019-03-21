package id.rizmaulana.movie.data.remote

import id.rizmaulana.movie.data.model.api.MovieRequest
import id.rizmaulana.movie.data.model.api.MovieResponse
import io.reactivex.Single

interface ApiHelper {

    fun getMovie(request: MovieRequest, type: String): Single<MovieResponse>


}
