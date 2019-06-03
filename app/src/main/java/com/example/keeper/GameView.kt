package com.example.keeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.keeper.database.entities.Game
import com.example.keeper.fragments.MainContentFragment
import com.example.keeper.utils.AppConstants

class GameView : AppCompatActivity() {


    private lateinit var mainFragment: MainContentFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_view)

        val receiver : Game = intent?.extras?.getParcelable(AppConstants.TEXT_KEY_GAME)!!
        //esto se arreglara al cambiar los fragmentos
        mainFragment = MainContentFragment.newInstance(receiver)
        changeFragment(R.id.main_cont_fragment, mainFragment)
    }

    private fun changeFragment(id: Int, frag: Fragment){ supportFragmentManager.beginTransaction().replace(id, frag).commit() }
}
