package com.example.game.model

data class Game(
    val id: Long,
    val playerX: String,
    val playerO: String,
    val board: String,
    val isFinished: Boolean,
    val winner: String?
)