package com.example.models

class Game {
    private lateinit var status: GameStatus
    private lateinit var currentTurnPlayer: Player
    private lateinit var moves: ArrayList<String>
    private var players : ArrayList<Player> = java.util.ArrayList<Player>()
    private var flag =0

    fun getStatus(): GameStatus {
        return status
    }

    fun setStatus(newStatus: GameStatus)
    {
        status = newStatus
    }

    fun setPlayerTurn(index: Int)
    {
        currentTurnPlayer = players[index]
        flag = (index+1)%2
    }

    fun getTurnPlayer(): Player {
        return currentTurnPlayer
    }

    fun setMoves(movesList: ArrayList<String>)
    {
        moves = movesList
    }

    fun getMoves(): ArrayList<String> {
        return moves
    }

    fun setPlayers(player: Player) {
        players.add(player)
    }

    fun getPlayers(): Player {
        return players[0]
    }

    fun checkWin(): Int
    {
        return 0
    }

}
