package id.rizmaulana.movie.ui.base


/**
 * Created by Rizki Maulana on 21/02/19.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */
interface BasePresenter<V : BaseViewHelper> {

    fun setView(viewHelper : V)

    fun onDestroy()


}