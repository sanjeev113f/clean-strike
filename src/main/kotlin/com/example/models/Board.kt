package com.example.models

import com.example.error.InValidMoveException

class CaromBoard {
    private var redCoins = COUNT_OF_RED_COINS
    private var blackCoins = COUNT_OF_BLACK_COINS
    private var striker = COUNT_OF_STRIKER

    fun getRedCoinsCount() = redCoins

    fun getBlackCoinsCount() = blackCoins

    fun getStrikerCount() = striker

    fun updateRedCoinsCount(value: Int) {
        checkRedCoins(value)
        redCoins += value
    }

    fun updateBlackCoinsCount(value: Int) {
        checkBlackCoins(value)
        blackCoins += value
    }

    fun isCoinsOver(): Boolean {
        if (blackCoins + redCoins == 0) return true
        return false
    }

    private fun checkBlackCoins(value: Int) {
        if (blackCoins + value < 0) {
            throw InValidMoveException()
        }
    }

    private fun checkRedCoins(value: Int) {
        if (redCoins + value < 0) {
            throw InValidMoveException()
        }
    }
}
