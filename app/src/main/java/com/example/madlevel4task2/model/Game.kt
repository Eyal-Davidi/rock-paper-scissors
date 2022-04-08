package com.example.madlevel4task2.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.madlevel4task2.GameMove
//import com.example.madlevel4task2.GameMove
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Entity(tableName = "gameTable")
@Parcelize
data class Game (
    @ColumnInfo(name = "game date")
    var gameDate: LocalDateTime,

    @ColumnInfo(name = "your move")
    var youMove: GameMove,

    @ColumnInfo(name = "Computer's move")
        var computerMove: GameMove,

    @ColumnInfo(name = "game result")
    var gameResult: String = "",

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
): Parcelable




