package com.example.keeper

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment

import androidx.lifecycle.ViewModelProviders
import com.example.keeper.database.entities.Game
import com.example.keeper.database.entities.GameViewModel
import com.example.keeper.fragments.ListFragment
import com.example.keeper.fragments.MainContentFragment
import com.example.keeper.utils.AppConstants

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity(), ListFragment.OnFragmentInteractionListener {



    override fun portraitItemClick(game: Game) {
        var mIntent = Intent(this, GameView::class.java)
        var bundle = Bundle()
        bundle.putParcelable(AppConstants.TEXT_KEY_GAME, game)
        mIntent.putExtras(bundle)
        startActivity(mIntent)
    }

    override fun landscapeItemClick(game: Game) {
        var contentFragment = MainContentFragment.newInstance(game)
        changeFragment(R.id.land_main_cont_fragment, contentFragment)
    }


    private lateinit var gameViewModel: GameViewModel
    private lateinit var listFragment: ListFragment
    private val newBookActivityRequestCode = 1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        listFragment = ListFragment.newInstance(true)
        val resource = if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            R.id.frameLayoutMain
        else {
            R.id.land_main_fragment
        }
       changeFragment(resource, listFragment)
        button_addGame.setOnClickListener { view ->
            val intent = Intent(this@MainActivity, NewGameActivity::class.java)
            startActivityForResult(intent, newBookActivityRequestCode)
                //Toast.makeText(this,"hofajo", Toast.LENGTH_SHORT).show()

        }
    }

    private fun changeFragment(id: Int, frag: Fragment){ supportFragmentManager.beginTransaction().replace(id, frag).commit() }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
