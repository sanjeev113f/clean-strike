package com.example.models

class Player(val person: Person) {
    private var gameScore = MIN_GAME_SCORE
    private var foulCount = MIN_COUNT
    private var threeSuccessiveTurnPoint = MIN_GAME_SCORE
    private var blackCoinCount = MIN_COUNT

    fun getBlackCoinCount() = blackCoinCount

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
