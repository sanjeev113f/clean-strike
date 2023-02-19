package com.example.models

class Player( val person:Person )
{
    private var gameScore = 0
    private var foulCount = 0
    private var threeSuccessiveTurnEarning = 0

    fun getGameScore(): Int {
       return gameScore
    }

    fun updateGameScore(value: Int)
    {
        gameScore = value
    }
}
