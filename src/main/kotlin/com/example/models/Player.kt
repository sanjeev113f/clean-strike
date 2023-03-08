package com.example.models

class Player(val person: Person) {
    private var gameScore = MIN_GAME_SCORE
    private var foulCount = MIN_COUNT
    private var threeSuccessiveTurnsCoins = MIN_COUNT
    private var blackCoinCount = MIN_COUNT
    private var redCoinCount = MIN_COUNT

    fun getBlackCoinCount() = blackCoinCount

    fun getRedCoinCount() = redCoinCount

    fun getGameScore() = gameScore

    fun getFoulCount() = foulCount

    fun getThreeSuccessiveTurnsCoins() = threeSuccessiveTurnsCoins

    fun updateFoulCount(value: Int) {
        foulCount += value
    }

    fun addRedCoin(value: Int) {
        redCoinCount += value
    }

    fun addBlackCoin(value: Int) {
        blackCoinCount += value
    }

    fun updateGameScore(value: Int) {
        gameScore += value
    }

    fun updateThreeSuccessiveTurnsCoins(value: Int) {
        threeSuccessiveTurnsCoins += value
    }
}
