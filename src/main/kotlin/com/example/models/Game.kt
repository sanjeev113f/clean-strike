package com.example.models

class Game(){
    private lateinit var status: GameStatus
    private lateinit var currentTurnPlayer: Player
    private lateinit var moves: ArrayList<String>
    private lateinit var players : ArrayList<Player>

    fun getStatus(): GameStatus {

        return status
    }

    fun setStatus(newStatus: GameStatus)
    {
        status = newStatus
    }


}
