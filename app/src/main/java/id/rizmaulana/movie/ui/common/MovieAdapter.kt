package id.rizmaulana.movie.ui.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.rizmaulana.movie.R
import id.rizmaulana.movie.data.model.db.MovieDto
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Created by Rizki Maulana on 19/03/19.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */
class MovieAdapter() : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    var listData: MutableList<MovieDto?> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
    )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(listData.get(position))
    }

    inner class ViewHolder(viewHolder: View) : RecyclerView.ViewHolder(viewHolder) {
        fun bindView(itemData: MovieDto?) {
            itemData?.let {
                Picasso.get().load("http://image.tmdb.org/t/p/w185/${it.poster_path}")
                    .into(itemView.appCompatImageView2)
                Picasso.get().load("http://image.tmdb.org/t/p/w500/${it.backdrop_path}")
                    .into(itemView.appCompatImageView)
                itemView.appCompatTextView.text = it.title
                itemView.appCompatTextView4.text = "${it.vote_average}"
                itemView.appCompatTextView3.text = it.overview

            }

        }

    }


    fun addAll(dataList: List<MovieDto?>) {
        listData.addAll(dataList)
        notifyDataSetChanged()
    }

    fun clear() {
        listData.clear()
        notifyDataSetChanged()
    }
}

/*
* http://image.tmdb.org/t/p/w185/AtsgWhDnHTq68L0lLsUrCnM7TjG.jpg
* */