package com.example.madlevel4task2.repository

import android.content.Context
import com.example.madlevel4task2.database.GameRoomDatabase
import com.example.madlevel4task2.dao.GameDao
import com.example.madlevel4task2.model.Game

class GameRepository(context: Context) {

    private var gameDao: GameDao

    init {
        val gameRoomDatabase = GameRoomDatabase.getDatabase(context)
        gameDao = gameRoomDatabase!!.gameDao()
    }

    suspend fun getAllGames(): List<Game> {
        return gameDao.getAllGames()
    }

    suspend fun insertGame(game: Game) {
        gameDao.insertGame(game)
    }

    suspend fun deleteAllProducts() {
        gameDao.deleteAllProducts()
    }

    suspend fun countWins(): String {
        return gameDao.countWins()
    }

    suspend fun countDraws(): String {
        return gameDao.countDraws()
    }

    suspend fun countLoses(): String {
        return gameDao.countLoses()
    }
}