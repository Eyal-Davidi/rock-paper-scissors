package com.example.madlevel4task2.dao

import androidx.room.*
import com.example.madlevel4task2.model.Game

@Dao
interface GameDao {

        @Query("SELECT * FROM gameTable")
        suspend fun getAllGames(): List<Game>

        @Insert
        suspend fun insertGame(game: Game)

        @Query("DELETE FROM gameTable")
        suspend fun deleteAllProducts()

        @Query("SELECT COUNT(`game result`) FROM 'gameTable' WHERE `game result` == 'You win!'")
        suspend fun countWins(): String

        @Query("SELECT COUNT(`game result`) FROM 'gameTable' WHERE `game result` == 'Draw'")
        suspend fun countDraws(): String

        @Query("SELECT COUNT(`game result`) FROM 'gameTable' WHERE `game result` == 'Computer wins!'")
        suspend fun countLoses(): String
}