package com.example.models

import com.example.error.OutOfIndexException
import com.example.error.PlayerLimitExceededException

class Game(private val board: CaromBoard = CaromBoard()) {
    private lateinit var currentTurnPlayer: Player
    private lateinit var winner: Player
    private var players: ArrayList<Player> = java.util.ArrayList<Player>()
    private var currentTurnPlayerIndex = START_INDEX
    private var status = GameStatus.INACTIVE
    private var lastThreeTurnsCoins = 0

    fun getStatus() = status

    fun getTurnPlayer() = currentTurnPlayer

    fun getWinner() = winner

    fun setStatus(newStatus: GameStatus) {
        status = newStatus
    }

    fun setPlayerTurn(index: Int) {
        if (index < 0 || index > PLAYERS_COUNT) throw OutOfIndexException()
        currentTurnPlayer = players[index]
        currentTurnPlayerIndex = (index + 1) % PLAYERS_COUNT
    }

    fun isCoinsOver(): Boolean {
        if (board.getBlackCoinsCount() + board.getRedCoinsCount() == 0) return true
        return false
    }

    fun addPlayers(player: Player) {
        if (players.size == PLAYERS_COUNT) throw PlayerLimitExceededException()
        players.add(player)
    }

    fun checkWin(): Boolean {
        val score1 = players[0].getGameScore()
        val score2 = players[1].getGameScore()
        if ((score1 >= 5 && score1 >= score2 + 3) || (score2 >= 5 && score2 >= score1 + 3)) {
            winner = currentTurnPlayer
            return true
        }
        return false
    }

    fun play(move: String) {
        if (status == GameStatus.INACTIVE) setStatus(GameStatus.ACTIVE)
        setPlayerTurn(currentTurnPlayerIndex)
        lastThreeTurnsCoins = currentTurnPlayer.getThreeSuccessiveTurnsCoins()

        when (move) {
            "Strike" -> {
                board.updateBlackCoinsCount(-1)
                currentTurnPlayer.updateGameScore(1)
                currentTurnPlayer.addBlackCoin(1)
                currentTurnPlayer.updateThreeSuccessiveTurnsCoins(-1 * lastThreeTurnsCoins)
            }

            "Multi strike" -> {
                currentTurnPlayer.updateGameScore(MULTI_STRIKE_SCORE)
                currentTurnPlayer.addBlackCoin(MULTI_STRIKE_COINS_COUNT)
                board.updateBlackCoinsCount(-1 * MULTI_STRIKE_COINS_COUNT)
                currentTurnPlayer.updateThreeSuccessiveTurnsCoins(-lastThreeTurnsCoins)
            }

            "Red strike" -> {
                board.updateRedCoinsCount(-1)
                currentTurnPlayer.updateGameScore(RED_STRIKE_SCORE)
                currentTurnPlayer.addRedCoin(1)
                currentTurnPlayer.updateThreeSuccessiveTurnsCoins(-1 * lastThreeTurnsCoins)
            }

            "Striker strike" -> {
                currentTurnPlayer.updateGameScore(-1)
                currentTurnPlayer.updateFoulCount(1)
                currentTurnPlayer.updateThreeSuccessiveTurnsCoins(1)
            }


            "Defunct coin" -> {
                board.updateBlackCoinsCount(-1)
                currentTurnPlayer.updateGameScore(DEFUNCT_COIN_PENALTY)
                currentTurnPlayer.updateFoulCount(1)
                currentTurnPlayer.updateThreeSuccessiveTurnsCoins(1)
            }
        }
        if (checkWin()) {
            setStatus(GameStatus.INACTIVE)
        }

        if (isCoinsOver()) {
            setStatus(GameStatus.DRAW)
        }
        checkAndUpdateForNullTurns()
        checkAndUpdateForFouls()
    }

    private fun checkAndUpdateForFouls() {
        if (currentTurnPlayer.getFoulCount() == MAX_FOUL_COUNT) {
            currentTurnPlayer.updateGameScore(NORMAL_PENALTY)
            currentTurnPlayer.updateFoulCount(-MAX_FOUL_COUNT)
        }
    }

    private fun checkAndUpdateForNullTurns() {
        if (currentTurnPlayer.getThreeSuccessiveTurnsCoins() == MAX_NULL_TURNS) {
            currentTurnPlayer.updateGameScore(NORMAL_PENALTY)
            currentTurnPlayer.updateThreeSuccessiveTurnsCoins(-MAX_NULL_TURNS)
        }
    }
}
