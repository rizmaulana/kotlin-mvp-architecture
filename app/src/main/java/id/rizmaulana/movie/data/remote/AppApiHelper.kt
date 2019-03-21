package id.rizmaulana.movie.data.remote

import id.rizmaulana.movie.data.model.api.MovieRequest
import id.rizmaulana.movie.data.model.api.MovieResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppApiHelper
@Inject
constructor(val serviceApi: ServiceApi) : ApiHelper {
    override fun getMovie(request: MovieRequest, type: String) = serviceApi.getMovie(type, request.toMap())



}

