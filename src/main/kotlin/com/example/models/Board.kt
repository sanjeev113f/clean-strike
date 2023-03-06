package com.example.models

class CaromBoard {
    private var redCoins = COUNT_OF_RED_COINS
    private var blackCoins = COUNT_OF_BLACK_COINS
    private var striker = COUNT_OF_STRIKER

    fun getRedCoinsCount() = redCoins

    fun getBlackCoinsCount() = blackCoins

    fun getStrikerCount() = striker

    fun updateRedCoinsCount(value: Int) {
        redCoins += value
    }

    fun updateBlackCoinsCount(value: Int) {
        blackCoins += value
    }
}