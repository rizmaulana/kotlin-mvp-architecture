package id.rizmaulana.movie.ui.movie

import id.rizmaulana.movie.ui.base.BasePresenter

/**
 * Created by Rizki Maulana on 19/03/19.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */
interface MoviePresenter: BasePresenter<MovieViewHelper>{

    fun setSearch(isSearch: Boolean)

    fun performSearch(keyword: String)

    fun setupSearch()

    fun loadFirst()

    fun loadMore()

}