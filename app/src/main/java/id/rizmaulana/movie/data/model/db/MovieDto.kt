package id.rizmaulana.movie.data.model.db

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by Rizki Maulana on 19/03/19.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */
@Entity
data class MovieDto(
    @Id(assignable = true)
    var id: Long?,
    var vote_count: Int?,
    var vote_average: Double?,
    var title: String?,
    var popularity: Double?,
    var poster_path: String?,
    var original_language: String?,
    var backdrop_path: String?,
    var overview: String?
)