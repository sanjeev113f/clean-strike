package com.example.models

class CaromBoard {
    private var redCoins = 1
    private var blackCoins = 9
    private var striker = 1

    fun getRedCoinsCount(): Int {
        return redCoins
    }

    fun getBlackCoinsCount(): Int {
        return blackCoins
    }

    fun getStrikerCount(): Int {
        return striker
    }

    fun updateRedCoinsCount(value: Int)
    {
        redCoins+=value
    }

    fun updateBlackCoinsCount(value: Int)
    {
        blackCoins+=value
    }
}