package com.example.moving

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class TVAdapter (var tvlist: MutableList<TV>, var onTVClick: (tv: TV) -> Unit
) : RecyclerView.Adapter<TVAdapter.TvViewHolder>(){

    inner class TvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val poster: ImageView = itemView.findViewById(R.id.item_tv_poster)
        fun bind(tv: TV) {
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${tv.poster_path}")
                .transform(CenterCrop())
                .into(poster)
            itemView.findViewById<TextView>(R.id.item_tv_title).text = tv.name
            itemView.setOnClickListener { onTVClick.invoke(tv) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_tv, parent, false)
        return TvViewHolder(view)
    }

    override fun getItemCount(): Int = tvlist.size

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        holder.bind(tvlist[position])
    }

    fun appendTV(tvlist: List<TV>) {
        this.tvlist.addAll(tvlist)
        notifyItemRangeInserted(
            this.tvlist.size,
            tvlist.size - 1
        )
    }

    fun removeTV(tvlist: List<TV>) {
        this.tvlist.removeAll(tvlist)
    }
}