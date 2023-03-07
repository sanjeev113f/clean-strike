package com.example.models

class Game(private val board: CaromBoard = CaromBoard()) {
    private lateinit var status: GameStatus
    private lateinit var currentTurnPlayer: Player
    private lateinit var winner: Player
    private var players: ArrayList<Player> = java.util.ArrayList<Player>()
    private var flag = 0

    fun getStatus() = status

    fun getTurnPlayer() = currentTurnPlayer

    fun getWinner() = winner

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

    fun play(move: String) {
        setStatus(GameStatus.ACTIVE)
        setPlayerTurn(flag)
        if (currentTurnPlayer.getFoulCount() == 3) {
            currentTurnPlayer.updateGameScore(-1)
            currentTurnPlayer.updateFoulCount(-3)
        }

        when (move) {
            "Strike" -> {
                CaromBoard().updateBlackCoinsCount(-1)
                currentTurnPlayer.updateGameScore(1)
                currentTurnPlayer.addBlackCoin(1)
            }

            "Multi strike" -> {
                currentTurnPlayer.updateGameScore(2)
                currentTurnPlayer.addBlackCoin(2)
                CaromBoard().updateBlackCoinsCount(-2)
            }

            "Red strike" -> {
                CaromBoard().updateRedCoinsCount(-1)
                currentTurnPlayer.updateGameScore(3)
                currentTurnPlayer.addRedCoin(1)
            }

            "Striker strike" -> {
                currentTurnPlayer.updateGameScore(-1)
                currentTurnPlayer.updateFoulCount(1)
            }


            "Defunct coin" -> {
                CaromBoard().updateBlackCoinsCount(-1)
                currentTurnPlayer.updateGameScore(-2)
                currentTurnPlayer.updateFoulCount(1)
            }
        }

        if (checkWin() == 1) {
            GameStatus.INACTIVE
            winner = currentTurnPlayer
        }

        if (isCoinsOver()) {
            GameStatus.Draw
        }
    }
}
