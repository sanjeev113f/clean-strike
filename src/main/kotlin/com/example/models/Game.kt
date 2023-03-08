package com.example.models

import com.example.error.PlayerLimitExceededException

class Game(private val board: CaromBoard = CaromBoard()) {
    private var winner: Player? = null
    private var players: MutableList<Player> = mutableListOf()
    private var currentTurnPlayerIndex = START_INDEX
    private var status = GameStatus.INACTIVE

    fun getStatus() = status

    fun getTurnPlayer() = players[currentTurnPlayerIndex]

    fun getWinner() = winner

    fun setStatus(newStatus: GameStatus) {
        status = newStatus
    }

    private fun setPlayerTurn(index: Int) {
        players[currentTurnPlayerIndex] = players[index]
        currentTurnPlayerIndex = (index + 1) % PLAYERS_COUNT
    }

    fun addPlayers(player: Player) {
        if (players.size == PLAYERS_COUNT) throw PlayerLimitExceededException()
        players.add(player)
    }

    fun checkWin(): Boolean {
        val score1 = players[0].getGameScore()
        val score2 = players[1].getGameScore()
        if ((score1 >= 5 && score1 >= score2 + 3) || (score2 >= 5 && score2 >= score1 + 3)) {
            winner = players[currentTurnPlayerIndex]
            return true
        }
        return false
    }

    fun play(move: String) {
        if (status == GameStatus.INACTIVE) setStatus(GameStatus.ACTIVE)
        setPlayerTurn(currentTurnPlayerIndex)
        lastThreeTurnsCoins = players[currentTurnPlayerIndex].getThreeSuccessiveTurnsCoins()
        executeMove(move)

        if (checkWin()) {
            setStatus(GameStatus.INACTIVE)
        }

        if (board.isCoinsOver()) {
            setStatus(GameStatus.DRAW)
        }
        checkAndUpdateForNullTurns()
        checkAndUpdateForFouls()
    }

    private var lastThreeTurnsCoins = MIN_COUNT
    private fun executeMove(move: String) {
        when (move) {
            "Strike" -> {
                updateCoinAndScore(1, 0, 1)
                updateFoulsAndTurnPoint(0, -1 * lastThreeTurnsCoins)
            }

            "Multi strike" -> {
                updateCoinAndScore(MULTI_STRIKE_COINS_COUNT, 0, MULTI_STRIKE_SCORE)
                updateFoulsAndTurnPoint(0, -1 * lastThreeTurnsCoins)

            }

            "Red strike" -> {
                updateCoinAndScore(0, 1, RED_STRIKE_SCORE)
                updateFoulsAndTurnPoint(0, -1 * lastThreeTurnsCoins)
            }

            "Striker strike" -> {
                updateCoinAndScore(0, 0, STRIKER_PENALTY_VALUE)
                updateFoulsAndTurnPoint(1, 1)
            }

            "Defunct coin" -> {
                updateCoinAndScore(-1, 0, DEFUNCT_COIN_PENALTY)
                updateFoulsAndTurnPoint(1, 1)
            }
        }
    }

    private fun updateCoinAndScore(blackCoins: Int, redCoins: Int, score: Int) {
        players[currentTurnPlayerIndex].updateGameScore(score)
        players[currentTurnPlayerIndex].addBlackCoin(blackCoins)
        players[currentTurnPlayerIndex].addRedCoin(redCoins)
        board.updateBlackCoinsCount(-1 * blackCoins)
        board.updateRedCoinsCount(-1 * blackCoins)
    }

    private fun updateFoulsAndTurnPoint(foulPoints: Int, successiveTurnUpdate: Int) {
        players[currentTurnPlayerIndex].updateFoulCount(foulPoints)
        players[currentTurnPlayerIndex].updateThreeSuccessiveTurnsCoins(successiveTurnUpdate)
    }

    private fun checkAndUpdateForFouls() {
        if (players[currentTurnPlayerIndex].getFoulCount() == MAX_FOUL_COUNT) {
            players[currentTurnPlayerIndex].updateGameScore(NORMAL_PENALTY)
            players[currentTurnPlayerIndex].updateFoulCount(-1 * MAX_FOUL_COUNT)
        }
    }

    private fun checkAndUpdateForNullTurns() {
        if (players[currentTurnPlayerIndex].getThreeSuccessiveTurnsCoins() == MAX_NULL_TURNS) {
            players[currentTurnPlayerIndex].updateGameScore(NORMAL_PENALTY)
            players[currentTurnPlayerIndex].updateThreeSuccessiveTurnsCoins(-1 * MAX_NULL_TURNS)
        }
    }
}
