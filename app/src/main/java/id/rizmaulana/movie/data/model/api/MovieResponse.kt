package id.rizmaulana.movie.data.model.api

import id.rizmaulana.movie.data.model.db.MovieDto

/**
 * Created by Rizki Maulana on 19/03/19.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */
data class MovieResponse(
    var page: Int?,
    var total_results: Int?,
    var total_pages: Int?,
    var results: List<MovieDto>?
)
