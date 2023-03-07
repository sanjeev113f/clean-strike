package com.example.models

import com.example.error.OutOfIndexError
import com.example.error.PlayerLimitExceededError

class Game(private val board: CaromBoard = CaromBoard()) {
    private lateinit var currentTurnPlayer: Player
    private lateinit var winner: Player
    private var players: ArrayList<Player> = java.util.ArrayList<Player>()
    private var currentTurnPlayerIndex = START_INDEX
    private var status = GameStatus.INACTIVE

    fun getStatus() = status

    fun getTurnPlayer() = currentTurnPlayer

    fun getWinner() = winner

    fun setStatus(newStatus: GameStatus) {
        status = newStatus
    }

    fun setPlayerTurn(index: Int) {
        if(index<0 || index> PLAYERS_COUNT) throw OutOfIndexError()
        currentTurnPlayer = players[index]
        currentTurnPlayerIndex = (index + 1) % PLAYERS_COUNT
    }

    fun isCoinsOver(): Boolean {
        if (board.getBlackCoinsCount() + board.getRedCoinsCount() == 0) return true
        return false
    }

    fun addPlayers(player: Player) {
        if(players.size == PLAYERS_COUNT) throw PlayerLimitExceededError()
        players.add(player)
    }

    fun checkWin(): Boolean {
        val score1 = players[0].getGameScore()
        val score2 = players[1].getGameScore()
        if ((score1 >= 5 && score1 >= score2 + 3) || (score2 >= 5 && score2 >= score1 + 3)) {
            return true
        }
        return false
    }

    fun play(move: String) {
        if(status == GameStatus.INACTIVE) setStatus(GameStatus.ACTIVE)
        setPlayerTurn(currentTurnPlayerIndex)
        checkAndUpdateForFouls()

        when (move) {
            "Strike" -> {
                board.updateBlackCoinsCount(-1)
                currentTurnPlayer.updateGameScore(1)
                currentTurnPlayer.addBlackCoin(1)
            }

            "Multi strike" -> {
                currentTurnPlayer.updateGameScore(2)
                currentTurnPlayer.addBlackCoin(2)
                board.updateBlackCoinsCount(-2)
            }

            "Red strike" -> {
                board.updateRedCoinsCount(-1)
                currentTurnPlayer.updateGameScore(3)
                currentTurnPlayer.addRedCoin(1)
            }

            "Striker strike" -> {
                currentTurnPlayer.updateGameScore(-1)
                currentTurnPlayer.updateFoulCount(1)
            }


            "Defunct coin" -> {
                board.updateBlackCoinsCount(-1)
                currentTurnPlayer.updateGameScore(-2)
                currentTurnPlayer.updateFoulCount(1)
            }
        }

        if (checkWin()) {
            setStatus(GameStatus.INACTIVE)
            winner = currentTurnPlayer
        }

        if (isCoinsOver()) {
            setStatus(GameStatus.DRAW)
        }
    }

    private fun checkAndUpdateForFouls() {
        if (currentTurnPlayer.getFoulCount() == MAX_FOUL_COUNT) {
            currentTurnPlayer.updateGameScore(-1)
            currentTurnPlayer.updateFoulCount(-MAX_FOUL_COUNT)
        }
    }
}
