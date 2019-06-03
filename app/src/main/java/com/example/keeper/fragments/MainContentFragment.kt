package com.example.keeper.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders

import com.example.keeper.R
import com.example.keeper.database.entities.Game
import com.example.keeper.database.entities.GameViewModel
import com.example.keeper.utils.AppConstants
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_new_game.view.*
import kotlinx.android.synthetic.main.fragment_main_content.view.*


class MainContentFragment : Fragment() {

    lateinit var game : Game
    lateinit var gameViewModel: GameViewModel

    companion object{
        fun newInstance (game: Game):MainContentFragment{
            val newFragment = MainContentFragment()
            newFragment.game = game
            return  newFragment
        }
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_main_content,container,false)

        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        if(savedInstanceState != null) game = savedInstanceState.getParcelable(AppConstants.TEXT_KEY_GAME)

        bindData(view)
        return view
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(AppConstants.TEXT_KEY_GAME,game)
    }

    fun bindData(view: View) {


        view.textview_nombre.text = game.nombre
        view.textview_equipoA.text = game.equipoA
        view.textview_equipoB.text = game.equipoB
        view.textview_puntosA.text = game.puntosA.toString()
        view.textview_puntosB.text = game.puntosB.toString()
        view.textview_fechaHora.text = game.fecha + " " + game.tiempo





    }

}
