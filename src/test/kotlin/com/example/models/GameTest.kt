package com.example.models

import com.example.error.InSufficientPlayersException
import com.example.error.InValidMoveException
import com.example.error.PlayerLimitExceededException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GameTest {

    @Test
    fun `should not be able to add player more than 2`() {
        val game = Game()
        game.addPlayers(Player("sanjeev"))
        game.addPlayers(Player("rao"))

        assertThrows<PlayerLimitExceededException> { game.addPlayers(Player("sanjiv")) }
    }

    @Test
    fun `should raise exception with single player`() {
        val game = Game()
        val player1 = Player("a")

        game.addPlayers(player1)

        assertThrows<InSufficientPlayersException> { game.play("abc") }
    }

    @Test
    fun `should be able to check winner and active status`() {
        val game = Game()
        val player1 = Player("a")
        val player2 = Player("b")

        game.addPlayers(player1)
        game.addPlayers(player2)
        game.play("Strike")

        assertEquals(null, game.getWinner())
        assertEquals(GameStatus.ACTIVE, game.getStatus())
    }

    @Test
    fun `should be able to check won player and game status`() {
        val board = CaromBoard()
        val game = Game(board)
        val player1 = Player("a")
        val player2 = Player("b")
        val ls = listOf(
            "Multi strike",
            "Strike",
            "Multi strike",
            "None",
            "Multi strike",
            "None"
        )

        game.addPlayers(player1)
        game.addPlayers(player2)
        for (moves in ls) {
            game.play(moves)
        }

        assertEquals(GameStatus.OVER, game.getStatus())
        assertNotNull(game.getWinner())
        assertEquals(player1, game.getWinner())
        assertEquals("a", game.getWinner()!!.getName())
    }

    @Test
    fun `should be able to check wining player and game status`() {
        val board = CaromBoard()
        val game = Game(board)
        val player1 = Player("a")
        val player2 = Player("b")
        val ls = listOf(
            "Multi strike",
            "Red strike",
            "Multi strike",
            "None",
            "Strike",
            "Striker strike"
        )

        game.addPlayers(player1)
        game.addPlayers(player2)
        for (moves in ls) {
            game.play(moves)
        }

        assertEquals(GameStatus.OVER, game.getStatus())
        assertEquals(player1, game.getWinner())
    }

    @Test
    fun `should be able to throw exception for invalid move`() {
        val game = Game()
        val player1 = Player("a")
        val player2 = Player("b")

        game.addPlayers(player1)
        game.addPlayers(player2)

        assertThrows<InValidMoveException> { game.play("abc") }
    }

    @Test
    fun `should be able to check winner and Draw status`() {
        val board = CaromBoard()
        val game = Game(board)
        val player1 = Player("a")
        val player2 = Player("b")
        val ls = listOf(
            "Striker strike",
            "Red strike",
            "Multi strike",
            "Striker strike",
            "Strike",
            "Strike",
            "Strike",
            "Striker strike",
            "Striker strike",
            "Strike",
            "Strike",
            "Striker strike",
            "Striker strike",
            "Strike",
            "Strike"
        )

        game.addPlayers(player1)
        game.addPlayers(player2)
        for (moves in ls) {
            game.play(moves)
        }

        assertEquals(GameStatus.DRAW, game.getStatus())
        assertEquals(null, game.getWinner())
    }

    @Test
    fun `should be able to check score after three turns with 0 coins pocketed and status and game winner`() {
        val game = Game()
        val player1 = Player("a")
        val player2 = Player("b")
        val ls = listOf(
            "Defunct coin",
            "Defunct coin",
            "Defunct coin",
            "Defunct coin",
            "Defunct coin",
            "Defunct coin"
        )
        val expected1 = 3 * DEFUNCT_COIN_PENALTY + 2 * NORMAL_PENALTY
        val expected2 = 3 * DEFUNCT_COIN_PENALTY + 2 * NORMAL_PENALTY


        game.addPlayers(player1)
        game.addPlayers(player2)
        for (moves in ls) {
            game.play(moves)
        }

        assertEquals(expected1, player1.getGameScore())
        assertEquals(expected2, player2.getGameScore())
        assertEquals(null, game.getWinner())
        assertEquals(GameStatus.ACTIVE, game.getStatus())
    }
}