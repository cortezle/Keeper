package com.example.keeper.database.entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = arrayOf(Game::class),version = 2)


public abstract class GameRoomDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object {
        @Volatile

        private var INSTANCE: GameRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): GameRoomDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameRoomDatabase::class.java,
                    "Game_database"
                )
                    .addCallback(BookDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                return instance
            }
        }

        private class BookDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                            if (database.gameDao().verify().size == 0) populateDatabase(database.gameDao())

                    }
                }
            }
        }


        suspend fun populateDatabase(gameDao: GameDao) {

            var game1 = Game("Lakers"+"vs"+"Cavs","Lakers", 12, "Cavaliers", 21,"05/04/2004","12:30 am", "lakerscavs")
            gameDao.insert(game1)

            var game2 = Game("Rockets"+"vs"+"Warriors","Rockets", 122, "Warriors", 121, "05/04/2004","12:30 am","lakerscavs2")
            gameDao.insert(game2)


        }
    }
}