package com.example.models

class CaromBoard {
    private var redCoins = 1
    private var blackCoins = 9
    private var striker = 1

    fun getRedCoins(): Int {
        return redCoins
    }

    fun getBlackCoins(): Int {
        return blackCoins
    }

    fun getStrikerCount(): Int {
        return striker
    }



}