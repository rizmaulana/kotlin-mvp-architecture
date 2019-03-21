package id.rizmaulana.movie.ui.movie

import id.rizmaulana.movie.data.model.db.MovieDto
import id.rizmaulana.movie.ui.base.BaseViewHelper

/**
 * Created by Rizki Maulana on 19/03/19.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */
interface MovieViewHelper: BaseViewHelper {

    fun add(movies: List<MovieDto>)

    fun clear()

    fun showProgress()

    fun hideProgress()

    fun showError()

    fun onReachEndOfList()


}