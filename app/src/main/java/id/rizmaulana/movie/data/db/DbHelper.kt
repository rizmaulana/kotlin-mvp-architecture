package id.rizmaulana.movie.data.db

import id.rizmaulana.movie.data.model.db.MovieDto

/**
 * Created by Rizki Maulana on 20/03/19.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */
interface DbHelper{

    fun getAllMovie() : List<MovieDto>

    fun clearMovie()

    fun addAll(movies: List<MovieDto>)
}