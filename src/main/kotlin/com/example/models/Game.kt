package com.example.models

import com.example.error.InSufficientPlayersException
import com.example.error.InValidMoveException
import com.example.error.PlayerLimitExceededException

class Game(private val board: CaromBoard = CaromBoard()) {
    private var winner: Player? = null
    private var players: MutableList<Player> = mutableListOf()
    private var currentTurnPlayerIndex = START_INDEX
    private var status = GameStatus.INACTIVE

    fun getStatus() = status

    fun getWinner() = winner

    fun addPlayers(player: Player) {
        if (players.size == PLAYERS_COUNT) throw PlayerLimitExceededException()
        players.add(player)
    }

    fun play(move: String): GameStatus {
        if (status == GameStatus.OVER || status == GameStatus.DRAW) return status
        if (players.size < PLAYERS_COUNT) throw InSufficientPlayersException(players.size)
        if (status == GameStatus.INACTIVE) setStatus(GameStatus.ACTIVE)
        executeMove(move)
        checkAndUpdateForNonPocketedTurns()
        checkAndUpdateFoulCountAndScore()
        checkAndSetWinner()
        updateCurrentTurnPlayerIndex()

        if (winner != null) {
            setStatus(GameStatus.OVER)

        } else if (board.isCoinsOver()) {
            setStatus(GameStatus.DRAW)
        }
        return status
    }


    private fun executeMove(move: String) {
        val lastThreeTurnsCoins = players[currentTurnPlayerIndex].getThreeSuccessiveTurnsCoins()
        when (move) {
            "Strike" -> {
                updateCoinAndScore(1, 0, 1)
                updateFoulsAndSuccessiveTurnPoint(0, -1 * lastThreeTurnsCoins)
            }

            "Multi strike" -> {
                updateCoinAndScore(MULTI_STRIKE_COINS_COUNT, 0, MULTI_STRIKE_SCORE)
                updateFoulsAndSuccessiveTurnPoint(0, -1 * lastThreeTurnsCoins)

            }

            "Red strike" -> {
                updateCoinAndScore(0, 1, RED_STRIKE_SCORE)
                updateFoulsAndSuccessiveTurnPoint(0, -1 * lastThreeTurnsCoins)
            }

            "Striker strike" -> {
                updateCoinAndScore(0, 0, STRIKER_PENALTY_VALUE)
                updateFoulsAndSuccessiveTurnPoint(1, 1)
            }

            "Defunct coin" -> {
                updateCoinAndScore(-1, 0, DEFUNCT_COIN_PENALTY)
                updateFoulsAndSuccessiveTurnPoint(1, 1)
            }

            "None" -> {
                updateFoulsAndSuccessiveTurnPoint(0, 1)
            }

            else -> throw InValidMoveException()
        }
    }

    private fun updateCoinAndScore(blackCoins: Int, redCoins: Int, score: Int) {
        players[currentTurnPlayerIndex].updateGameScore(score)
        players[currentTurnPlayerIndex].addBlackCoin(blackCoins)
        players[currentTurnPlayerIndex].addRedCoin(redCoins)
        board.updateBlackCoinsCount(-1 * blackCoins)
        board.updateRedCoinsCount(-1 * redCoins)
    }

    private fun updateFoulsAndSuccessiveTurnPoint(foulPoints: Int, currentTurnUpdate: Int) {
        players[currentTurnPlayerIndex].updateFoulCount(foulPoints)
        players[currentTurnPlayerIndex].updateThreeSuccessiveTurnsCoins(currentTurnUpdate)
    }

    private fun checkAndUpdateFoulCountAndScore() {
        if (players[currentTurnPlayerIndex].getFoulCount() == MAX_FOUL_COUNT) {
            players[currentTurnPlayerIndex].updateGameScore(NORMAL_PENALTY)
            players[currentTurnPlayerIndex].updateFoulCount(-1 * MAX_FOUL_COUNT)
        }
    }

    private fun checkAndUpdateForNonPocketedTurns() {
        if (players[currentTurnPlayerIndex].getThreeSuccessiveTurnsCoins() == MAX_NON_POCKETED_TURNS_COUNT) {
            players[currentTurnPlayerIndex].updateGameScore(NORMAL_PENALTY)
            players[currentTurnPlayerIndex].updateThreeSuccessiveTurnsCoins(-1 * MAX_NON_POCKETED_TURNS_COUNT)
        }
    }

    private fun setStatus(newStatus: GameStatus) {
        status = newStatus
    }

    private fun updateCurrentTurnPlayerIndex() {
        currentTurnPlayerIndex = (currentTurnPlayerIndex + 1) % PLAYERS_COUNT
    }

    private fun checkAndSetWinner(): Player? {

        val score1 = players[currentTurnPlayerIndex].getGameScore()
        val score2 = players[(currentTurnPlayerIndex + 1) % PLAYERS_COUNT].getGameScore()
        if ((score1 >= MIN_INDIVIDUAL_SCORE_TO_WIN && score1 >= (score2 + MIN_RELATIVE_SCORE_FACTOR_TO_WIN))) {
            winner = players[currentTurnPlayerIndex]
            return winner
        }
        if ((score2 >= MIN_INDIVIDUAL_SCORE_TO_WIN && score2 >= (score1 + MIN_RELATIVE_SCORE_FACTOR_TO_WIN))) {
            winner = players[(currentTurnPlayerIndex + 1) % PLAYERS_COUNT]
            return winner
        }
        return null
    }
}
