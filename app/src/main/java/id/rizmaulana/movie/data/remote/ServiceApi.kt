package id.rizmaulana.movie.data.remote

import id.rizmaulana.movie.data.model.api.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * Created by Rizki Maulana on 08/03/19.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */
interface ServiceApi {

    @GET("{method}/movie")
    fun getMovie(@Path("method") type: String, @QueryMap request: HashMap<String, String>): Single<MovieResponse>


}