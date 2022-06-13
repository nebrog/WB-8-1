package com.example.seventhweekapppt1.ui.Heroes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.seventhweekapppt1.R
import com.example.seventhweekapppt1.data.api.DotaAPI.URL_IMG
import com.example.seventhweekapppt1.data.model.HeroesItem
import com.example.seventhweekapppt1.ui.Repository

class HeroesAdapter(
    private val onHeroClickListener: OnHeroClickListener,
) : RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>() {

    private val list = ArrayList<HeroesItem>()

    fun setData(map: Map<String, HeroesItem>) {
        list.clear()
        list.addAll(map.values.sortedBy { it.id })
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_items, parent, false)
        return HeroesViewHolder(item)
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val hero = list.get(position)
        holder.bindData(hero)
        holder.itemView.setOnClickListener { onHeroClickListener.onClick(hero) }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class HeroesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val heroImg = itemView.findViewById<ImageView>(R.id.img_hero)
        val heroName = itemView.findViewById<TextView>(R.id.hero_name)

        fun bindData(item: HeroesItem) {
            heroName.text = item.name
            heroImg.load(URL_IMG + item.img)


        }

    }
}