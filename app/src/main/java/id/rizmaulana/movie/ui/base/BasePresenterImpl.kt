package id.rizmaulana.movie.ui.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import id.rizmaulana.movie.data.DataManager
import id.rizmaulana.movie.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Rizki Maulana on 19/03/19.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */
abstract class BasePresenterImpl<V : BaseViewHelper> constructor(
    val compositeDisposable: CompositeDisposable,
    val dataManager: DataManager,
    val schedulerProvider: SchedulerProvider
) : BasePresenter<V>, LifecycleObserver {
    protected lateinit var viewHelper: V

    override fun setView(viewHelper: V) {
        this.viewHelper = viewHelper
        if (viewHelper is LifecycleOwner) {
            viewHelper.lifecycle.addObserver(this)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onDestroy() {
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }
}