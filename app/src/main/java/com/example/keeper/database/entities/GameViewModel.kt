package com.example.keeper.database.entities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel (application: Application): AndroidViewModel(application){

    private val repository: GameRepository
    val allGames: LiveData<List<Game>>

    init {
        val gamesDao = GameRoomDatabase.getDatabase(application,viewModelScope).gameDao()
        repository = GameRepository(gamesDao)
        allGames = repository.allGames
    }

    fun insert(game: Game) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(game)
    }
}
