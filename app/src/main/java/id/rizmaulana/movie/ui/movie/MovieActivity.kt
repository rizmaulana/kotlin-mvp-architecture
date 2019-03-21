package id.rizmaulana.movie.ui.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.miguelcatalan.materialsearchview.MaterialSearchView
import id.rizmaulana.movie.data.model.db.MovieDto
import id.rizmaulana.movie.ui.base.BaseActivity
import id.rizmaulana.movie.ui.common.MovieAdapter
import kotlinx.android.synthetic.main.activity_movie.*
import ru.alexbykov.nopaginate.paginate.NoPaginate
import javax.inject.Inject






class MovieActivity : BaseActivity(), MovieViewHelper {

    @Inject
    lateinit var presenter: MoviePresenter
    @Inject
    lateinit var layoutManager: LinearLayoutManager
    @Inject
    lateinit var adapter: MovieAdapter
    lateinit var pager: NoPaginate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(id.rizmaulana.movie.R.layout.activity_movie)
        setSupportActionBar(toolbar)
        setupRecyclerView()
        setupView()

        presenter.setView(this)
        presenter.loadFirst()
        presenter.setupSearch()

    }

    private fun setupRecyclerView() {
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = adapter
        recycler_view.hasFixedSize()
        pager = NoPaginate.with(recycler_view).setOnLoadMoreListener { presenter.loadMore() }.build()

    }

    private fun setupView() {
        swipe_layout.setOnRefreshListener {
            presenter.loadFirst()
            swipe_layout.post {
                swipe_layout.isRefreshing = false
            }
            pager.setNoMoreItems(false)
        }
        search_view.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isNotEmpty()){
                    presenter.performSearch(newText)
                }
                return false
            }
        })
        search_view.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener{
            override fun onSearchViewClosed() {
                presenter.setSearch(false)
                presenter.loadFirst()
            }

            override fun onSearchViewShown() {
                presenter.setSearch(true)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(id.rizmaulana.movie.R.menu.menu_movie, menu)
        val item = menu?.findItem(id.rizmaulana.movie.R.id.action_search)
        search_view.setMenuItem(item)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return super.onOptionsItemSelected(item)
    }

    override fun add(movies: List<MovieDto>) {
        adapter.addAll(movies)
    }

    override fun clear() {
        adapter.clear()
    }

    override fun showProgress() {
        pager.setNoMoreItems(true);
        pager.showLoading(true)
    }

    override fun hideProgress() {
        pager.setNoMoreItems(false);
        pager.showLoading(false)
    }

    override fun showError() {
        pager.setNoMoreItems(true);
        showToast(getString(id.rizmaulana.movie.R.string.msg_error))
    }

    override fun onReachEndOfList() {
        pager.setNoMoreItems(true);
    }
}
