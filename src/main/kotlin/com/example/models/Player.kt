package com.example.models

class Player(val person: Person) {
    private var gameScore = MIN_VALUE
    private var foulCount = MIN_VALUE
    private var threeSuccessiveTurnPoint = MIN_VALUE

    fun getGameScore() = gameScore

    fun getFoulCount() = foulCount

    fun getThreeSuccessiveTurnEarning() = threeSuccessiveTurnPoint

    fun updateFoulCount(value: Int) {
        foulCount += value
    }

    fun updateGameScore(value: Int) {
        gameScore += value
    }

    fun updateSuccessiveTurnEarning(value: Int) {
        threeSuccessiveTurnPoint += value
    }

}
