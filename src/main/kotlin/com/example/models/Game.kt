package com.example.models

class Game {
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

    fun setPlayerTurn(player: Player)
    {
        currentTurnPlayer = player
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
        players = java.util.ArrayList<Player>()
        players.add(player)
    }

    fun getPlayers(): Player {
        return players[0]
    }

    fun startGame(listOfMoves : ArrayList<String>)
    {
        setStatus(GameStatus.ACTIVE)
        setPlayerTurn(p1)

        for(i in listOfMoves)
        {

        }
    }


}
