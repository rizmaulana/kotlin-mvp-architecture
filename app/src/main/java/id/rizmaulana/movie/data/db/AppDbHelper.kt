package id.rizmaulana.movie.data.db

import id.rizmaulana.movie.data.model.db.MovieDto
import io.objectbox.BoxStore
import javax.inject.Inject

/**
 * Created by Rizki Maulana on 20/03/19.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */
class AppDbHelper
@Inject
constructor(val db: BoxStore) : DbHelper {

    override fun getAllMovie()= db.boxFor(MovieDto::class.java).all

    override fun clearMovie() {
        db.boxFor(MovieDto::class.java).removeAll()
    }


    override fun addAll(movies: List<MovieDto>) {
        db.boxFor(MovieDto::class.java).put(movies)
    }

}