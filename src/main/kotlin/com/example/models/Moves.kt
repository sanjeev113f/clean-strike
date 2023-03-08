package com.example.models

class Moves {
    private var validMoves: List<String> = listOf(
        "Strike",
        "Multi strike",
        "Red strike",
        "Striker strike",
        "Defunct coin"
    )
    fun getValidMoves(): List<String> = validMoves
}