package com.example.keeper.database.entities

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class GameRepository (private val gamedao: GameDao){


    val allGames: LiveData<List<Game>> = gamedao.getAllGames()

    @WorkerThread
    suspend fun insert(game: Game) {
        gamedao.insert(game)
    }


}