package com.example.models

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@MicronautTest
class GameTest {

    @Test
    fun `should be able set status for game`() {
        val game = Game()

        game.setStatus(GameStatus.ACTIVE)

        assertEquals(game.getStatus(), GameStatus.ACTIVE)
    }

    @Test
    fun `should be able set turn for players`() {
        val person = Person("sanjeev")
        val player = Player(person)
        val game = Game()

        game.addPlayers(player)
        game.setPlayerTurn(0)

        assertEquals(game.getTurnPlayer(), player)
    }

    @Test
    fun `should be able to check win`() {
        val game = Game()
        val player1 = Player(Person("a"))
        val player2 = Player(Person("b"))

        game.addPlayers(player1)
        game.addPlayers(player2)

        assertEquals(game.checkWin(), false)
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
        assertEquals(game.getStatus(), GameStatus.INACTIVE)
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

        assertEquals(game.checkWin(), true)
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

        assertEquals(game.getStatus(), GameStatus.ACTIVE)
    }

    @Test
    fun `should be able to check coins remaining`() {
        val board = CaromBoard()
        val game = Game(board)

        board.updateBlackCoinsCount(-9)
        board.updateRedCoinsCount(-1)

        assertEquals(game.isCoinsOver(), true)
    }

    @Test
    fun `should be able to play the game with win as result`() {
        val board = CaromBoard()
        val game = Game(board)
        val player1 = Player(Person("a"))
        val player2 = Player(Person("b"))

        game.addPlayers(player1)
        game.addPlayers(player2)
        val ls = listOf("Multi strike", "Strike", "Defunct coin", "Red strike", "Multi strike", "Defunct coin", "Red strike")
        for (moves in ls) {
            game.play(moves)
        }

        assertEquals(game.getStatus(), GameStatus.INACTIVE)
        assertEquals(game.getWinner().person.name, "a")
    }

    @Test
    fun `should be able to play the game with Draw as result`() {
        val board = CaromBoard()
        val game = Game(board)
        val player1 = Player(Person("a"))
        val player2 = Player(Person("b"))

        game.addPlayers(player1)
        game.addPlayers(player2)
        val ls = listOf("Striker strike", "Red strike", "Multi strike", " Striker strike", "Strike", "Strike", "Strike",
            "Striker strike", "Striker strike", "Strike", "Strike", "Striker strike", "Striker strike", "Strike", "Strike")
        for (moves in ls) {
            game.play(moves)
        }

        assertEquals(game.getStatus(), GameStatus.DRAW)
    }
}