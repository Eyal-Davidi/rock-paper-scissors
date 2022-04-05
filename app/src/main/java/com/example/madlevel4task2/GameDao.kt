package com.example.madlevel4task2

import androidx.room.*

@Dao
interface GameDao {

        @Query("SELECT * FROM gameTable")
        fun getAllGames(): List<Game>

        @Insert
        fun insertGame(game: Game)

        @Delete
        fun deleteGame(game: Game)

        @Update
        fun updateGame(game: Game)
}