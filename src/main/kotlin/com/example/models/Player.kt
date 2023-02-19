package com.example.models

class Player( val person:Person )
{
    private var gameScore = 0
    private var foulCount = 0
    private var threeSuccessiveTurnPoint = 0

    fun getGameScore(): Int {
       return gameScore
    }

    fun updateGameScore(value: Int)
    {
        gameScore += value
    }

    fun getFoulCount(): Int {
        return foulCount
    }

    fun updateFoulCount(value: Int)
    {
        foulCount += value
    }

    fun getThreeSuccessiveTurnEarning(): Int {
        return threeSuccessiveTurnPoint
    }

    fun updateSuccessiveTurnEarning(value :Int)
    {
        threeSuccessiveTurnPoint+=value
    }


}
