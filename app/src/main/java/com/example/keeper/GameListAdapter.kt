package com.example.keeper

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.keeper.database.entities.Game
import kotlinx.android.synthetic.main.fragment_main_content.view.*
import kotlinx.android.synthetic.main.game_model.view.*


class GameListAdapter (private val clickListener: (Game)-> Unit):RecyclerView.Adapter<GameListAdapter.ViewHolder>(){


    private var games = emptyList<Game>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.game_model,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder:GameListAdapter.ViewHolder, position: Int) {
        val current = games[position]
        holder.bind(current,clickListener)
    }

    internal fun setWords(games : List<Game>){
        this.games = games
        notifyDataSetChanged()
    }


    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){

        fun bind(item: Game,clickListener: (Game) -> Unit) = with(itemView){
            Log.d("HOLAAA",item.nombre)
            name_game.text = item.nombre
            dateTime_game.text = "Date & time:" + item.fecha + " " + item.tiempo
            this.setOnClickListener{clickListener(item)}
        }
    }
}