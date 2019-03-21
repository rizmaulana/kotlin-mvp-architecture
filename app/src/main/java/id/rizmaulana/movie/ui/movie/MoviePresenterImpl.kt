package id.rizmaulana.movie.ui.movie

import com.jakewharton.rxrelay2.PublishRelay
import id.rizmaulana.movie.data.DataManager
import id.rizmaulana.movie.data.model.api.MovieRequest
import id.rizmaulana.movie.data.remote.ApiEndPoint
import id.rizmaulana.movie.ui.base.BasePresenterImpl
import id.rizmaulana.movie.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Rizki Maulana on 19/03/19.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */
class MoviePresenterImpl
@Inject
constructor(
    cd: CompositeDisposable,
    dm: DataManager,
    sp: SchedulerProvider
) : BasePresenterImpl<MovieViewHelper>(cd, dm, sp), MoviePresenter {


    private var pageToLoad: Int = 0
    private val autoCompletePublishSubject = PublishRelay.create<String>()
    private var method: String = ApiEndPoint.Discover
    private var querySearch: String = ""

    override fun setupSearch() {
        compositeDisposable.add(
            autoCompletePublishSubject
                .debounce(500, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .switchMap {
                    val request = MovieRequest().apply {
                        pageToLoad = 1
                        querySearch = it

                        this.page = pageToLoad
                        this.query = querySearch
                    }
                    dataManager.getMovie(request, method).toObservable()
                }
                .doOnSubscribe {
                    viewHelper.showProgress()
                }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ response ->
                    viewHelper.hideProgress()
                    response.results?.let {
                        if (pageToLoad == 1) {
                            viewHelper.clear()
                            dataManager.clearMovie()
                        }
                        dataManager.addAll(it)
                        viewHelper.add(it)
                    }
                }, {
                    viewHelper.hideProgress()
                })
        )
    }

    override fun setSearch(isSearch: Boolean) {
        method = if (isSearch) ApiEndPoint.Search else ApiEndPoint.Discover
    }

    override fun performSearch(keyword: String) {
        autoCompletePublishSubject.accept(keyword)
    }

    override fun loadFirst() {
        pageToLoad = 0;
        loadMore()
    }

    override fun loadMore() {
        val request = MovieRequest().apply {
            pageToLoad += 1
            this.page = pageToLoad
            this.query = querySearch
        }

        compositeDisposable.add(
            dataManager.getMovie(request, method).observeOn(schedulerProvider.ui()).subscribeOn(
                schedulerProvider.io()
            ).doOnSubscribe {
                viewHelper.showProgress()
                if (pageToLoad == 1) {
                    val movieCache = dataManager.getAllMovie()
                    if (movieCache.isNotEmpty()) {
                        viewHelper.add(movieCache)
                    } else {
                        viewHelper.showError()
                    }
                }

            }.subscribe({ response ->
                viewHelper.hideProgress()
                response.results?.let {
                    if (pageToLoad == 1) {
                        viewHelper.clear()
                        dataManager.clearMovie();
                    }
                    if (it.isNotEmpty()) {
                        viewHelper.add(it)
                        dataManager.addAll(it)
                    } else {
                        viewHelper.onReachEndOfList()
                    }
                }
            }, {
                viewHelper.hideProgress()
                /*if (pageToLoad == 1) {
                    val movieCache = dataManager.getAllMovie()
                    if (movieCache.isNotEmpty()) {
                        viewHelper.add(movieCache)
                    } else {
                        viewHelper.showError()
                    }
                } else {
                    viewHelper.showError()
                }*/
            })
        )
    }


}