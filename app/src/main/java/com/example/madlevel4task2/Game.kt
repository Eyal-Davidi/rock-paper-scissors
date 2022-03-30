package com.example.madlevel4task2

import java.time.LocalDateTime

data class Game (
    ///game date type?


    val gameDate: LocalDateTime,
    var youMove: GameMove,
    var computerMove: GameMove,
    var winCounter: Int=0,
    var drawCounter: Int=0,
    var loseCounter: Int=0,
    var gameResult: String = "",
    )