package com.example.keeper.fragments

import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keeper.GameListAdapter

import com.example.keeper.R
import com.example.keeper.database.entities.Game
import com.example.keeper.database.entities.GameViewModel
import com.example.keeper.utils.AppConstants
import kotlinx.android.synthetic.main.fragment_list.view.*



class ListFragment : Fragment() {

    lateinit var gameViewModel: GameViewModel
    lateinit var gameadapter: GameListAdapter
    var flag : Boolean = true
    var click:OnFragmentInteractionListener? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        if(savedInstanceState != null) flag = savedInstanceState.getBoolean(AppConstants.FLAG_KEY)

        initRecyclerView(resources.configuration.orientation ,view)
        return view
    }

    fun initRecyclerView(orientation : Int, container: View){
        val linearLayoutManager = LinearLayoutManager(this.context)
        if(orientation == Configuration.ORIENTATION_PORTRAIT) gameadapter = GameListAdapter({ game : Game -> click?.portraitItemClick(game)})
        if(orientation == Configuration.ORIENTATION_LANDSCAPE) gameadapter = GameListAdapter( { game : Game -> click?.landscapeItemClick(game)})
        container.recyclerviewList.adapter = gameadapter
        if(flag){
            gameViewModel.allGames.observe(this, Observer { boooks ->
                boooks?.let{gameadapter.setWords(it)}
            })
        } else{
            /*gameViewModel.favoriteBooks.observe(this, Observer { boooks ->
                boooks?.let{bookadapter.setWords(it)}
            })*/
        }

        container.recyclerviewList.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(AppConstants.FLAG_KEY, flag)
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            click = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
         click = null
    }


    interface OnFragmentInteractionListener {
        fun portraitItemClick(game: Game)

        fun landscapeItemClick(game: Game)
    }


    companion object {
        fun newInstance(flag : Boolean): ListFragment{
            val newFragment = ListFragment()
            newFragment.flag = flag
            return newFragment
        }
    }
}
