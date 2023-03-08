package com.example.models

import com.example.error.PlayerLimitExceededException
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@MicronautTest
class GameTest {

    @Test
    fun `should be able set status for game`() {
        val game = Game()

        game.setStatus(GameStatus.ACTIVE)

        assertEquals(GameStatus.ACTIVE, game.getStatus())
    }

    @Test
    fun `should not be able add player more than 2`() {
        val game = Game()
        game.addPlayers(Player(Person("sanjeev")))
        game.addPlayers(Player(Person("rao")))

        assertThrows<PlayerLimitExceededException> { game.addPlayers(Player(Person("sanjiv"))) }
    }

    @Test
    fun `should be able to check win`() {
        val game = Game()
        val player1 = Player(Person("a"))
        val player2 = Player(Person("b"))

        game.addPlayers(player1)
        game.addPlayers(player2)

        assertEquals(false, game.checkWin())
    }

    @Test
    fun `should be able to check win and update status`() {
        val game = Game()
        val player1 = Player(Person("a"))
        val player2 = Player(Person("b"))

        game.addPlayers(player1)
        game.addPlayers(player2)
        player2.updateGameScore(5)

        assertEquals(game.checkWin(), true)
        assertEquals(GameStatus.INACTIVE, game.getStatus())
    }

    @Test
    fun `should be able to check win with updated score`() {
        val game = Game()
        val player1 = Player(Person("a"))
        val player2 = Player(Person("b"))

        game.addPlayers(player1)
        game.addPlayers(player2)
        player1.updateGameScore(10)
        player2.updateGameScore(2)

        assertEquals(true, game.checkWin())
    }

    @Test
    fun `should be able to give current turn player`() {
        val game = Game()
        val player1 = Player(Person("a"))
        val player2 = Player(Person("b"))
        val expected = player1

        game.addPlayers(player1)
        game.addPlayers(player2)

        assertEquals(player1, game.getTurnPlayer())
    }

    @Test
    fun `should be able to start game`() {
        val board = CaromBoard()
        val game = Game(board)

        val player1 = Player(Person("sanjeev"))
        val player2 = Player(Person("kumar"))
        game.addPlayers(player1)
        game.addPlayers(player2)
        game.play("strike")

        assertEquals(GameStatus.ACTIVE, game.getStatus())
    }

    @Test
    fun `should be able to check coins remaining`() {
        val board = CaromBoard()
        val game = Game(board)

        board.updateBlackCoinsCount(-9)
        board.updateRedCoinsCount(-1)

        assertEquals(true, game.isCoinsOver())
    }

    @Test
    fun `should be able to play the game with win as result`() {
        val board = CaromBoard()
        val game = Game(board)
        val player1 = Player(Person("a"))
        val player2 = Player(Person("b"))

        game.addPlayers(player1)
        game.addPlayers(player2)
        val ls = listOf(
            "Multi strike",
            "Strike",
            "Defunct coin",
            "Red strike",
            "Multi strike",
            "Defunct coin",
            "Red strike"
        )
        for (moves in ls) {
            game.play(moves)
        }

        assertEquals(game.getStatus(), GameStatus.INACTIVE)
        assertNotNull(game.getWinner())
        assertEquals("b", game.getWinner()!!.person.name)
    }

    @Test
    fun `should be able to play the game with Draw as result`() {
        val board = CaromBoard()
        val game = Game(board)
        val player1 = Player(Person("a"))
        val player2 = Player(Person("b"))

        game.addPlayers(player1)
        game.addPlayers(player2)
        val ls = listOf(
            "Striker strike",
            "Red strike",
            "Multi strike",
            " Striker strike",
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
        for (moves in ls) {
            game.play(moves)
        }

        assertEquals(GameStatus.DRAW, game.getStatus())
    }

    @Test
    fun `should be able to update score after three turns with 0 coins pocketed`() {
        val game = Game()
        val player1 = Player(Person("a"))
        val player2 = Player(Person("b"))
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
    }
}