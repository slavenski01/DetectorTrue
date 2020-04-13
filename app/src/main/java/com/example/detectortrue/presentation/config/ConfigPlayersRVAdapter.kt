package com.example.detectortrue.presentation.config

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.detectortrue.R
import com.example.detectortrue.presentation.base.BaseRVAdapter
import com.example.detectortrue.util.CONFIG_MAX_PLAYERS
import kotlinx.android.synthetic.main.rv_config_players_item.view.*

class ConfigPlayersRVAdapter(private val blockOnClick: (position: Int) -> Unit) :
    BaseRVAdapter<RecyclerView.ViewHolder>() {

    private val countMaxPlayers = CONFIG_MAX_PLAYERS
    private var selectedVariant: Int = 0

    private fun setSelectedVariant(position: Int) {
        selectedVariant = position
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = countMaxPlayers

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ConfigPlayersItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_config_players_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ConfigPlayersItemViewHolder).bind(position)
    }

    private inner class ConfigPlayersItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(position: Int) {

            itemView.apply {
                itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        if (position == selectedVariant) R.color.yellow_ffe else R.color.gray
                    )
                )
                tv_rv_config_players_number.text = (position + 2).toString()
                setOnClickListener {
                    setSelectedVariant(position)
                    blockOnClick.invoke(position + 2)
                }
            }
        }
    }
}