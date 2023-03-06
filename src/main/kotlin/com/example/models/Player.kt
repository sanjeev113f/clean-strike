package com.example.models

class Player(val person: Person) {
    private var gameScore = MIN_VALUE
    private var foulCount = MIN_VALUE
    private var threeSuccessiveTurnPoint = MIN_VALUE

    fun getGameScore(): Int {
        return gameScore
    }

    fun updateGameScore(value: Int) {
        gameScore += value
    }

    fun getFoulCount(): Int {
        return foulCount
    }

    fun updateFoulCount(value: Int) {
        foulCount += value
    }

    fun getThreeSuccessiveTurnEarning(): Int {
        return threeSuccessiveTurnPoint
    }

    fun updateSuccessiveTurnEarning(value: Int) {
        threeSuccessiveTurnPoint += value
    }


}
