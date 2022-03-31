package com.example.madlevel4task2

import java.time.LocalDateTime

data class Game (
    var gameDate: LocalDateTime,
    var youMove: GameMove,
    var computerMove: GameMove,
    var gameResult: String = "",
    )