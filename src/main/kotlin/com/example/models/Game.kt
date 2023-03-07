package com.example.models

class Game(private val board: CaromBoard = CaromBoard()) {
    private lateinit var status: GameStatus
    private lateinit var currentTurnPlayer: Player
    private lateinit var moves: ArrayList<String>
    private var players: ArrayList<Player> = java.util.ArrayList<Player>()
    private var flag = 0

    fun getStatus() = status

    fun getTurnPlayer() = currentTurnPlayer

    fun getMoves() = moves

    fun setStatus(newStatus: GameStatus) {
        status = newStatus
    }

    fun setPlayerTurn(index: Int) {
        currentTurnPlayer = players[index]
        flag = (index + 1) % 2
    }

    fun isCoinsOver(): Boolean {
        if (board.getBlackCoinsCount() + board.getRedCoinsCount() == 0) return true

        return false
    }

    fun setMoves(movesList: ArrayList<String>) {
        moves = movesList
    }

    fun addPlayers(player: Player) {
        players.add(player)
    }

    fun getPlayers(): Player {
        return players[0]
    }

    fun checkWin(): Int {
        val score1 = players[0].getGameScore()
        val score2 = players[1].getGameScore()
        if (score1 >= 5 && score1 >= score2 + 3) {
            setStatus(GameStatus.INACTIVE)
            return 1
        }

        if (score2 >= 5 && score2 >= score1 + 3) {
            setStatus(GameStatus.INACTIVE)
            return 1
        }
        return 0
    }

    fun play() {
        setStatus(GameStatus.ACTIVE)
    }
}
