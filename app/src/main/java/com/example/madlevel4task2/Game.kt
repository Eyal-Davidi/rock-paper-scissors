package com.example.madlevel4task2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Game (
        var gameDate: LocalDateTime,
        var youMove: GameMove,
        var computerMove: GameMove,
        var gameResult: String = "",
    ): Parcelable