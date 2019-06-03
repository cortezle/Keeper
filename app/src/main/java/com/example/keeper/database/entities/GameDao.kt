package com.example.keeper.database.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface GameDao {

    @Query("SELECT * from game_table ORDER BY idGame ASC")
    fun getAllGames(): LiveData<List<Game>>

    @Insert
    suspend fun insert(game: Game)

    @Query("DELETE FROM game_table")
    fun deleteAll()

    @Query("SELECT * FROM game_table")
    fun verify() : List<Game>
}