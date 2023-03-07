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
    fun `should be able set moves for players`() {
        val game = Game()
        val moves = ArrayList<String>()

        moves.add("strike")
        game.setMoves(moves)

        assertEquals(game.getMoves(), moves)
    }

    @Test
    fun `should be able add players`() {
        val game = Game()
        val person = Person("sanjeev")
        val player = Player(person)

        game.addPlayers(player)

        assertEquals(game.getPlayers(), player)
    }

    @Test
    fun `should be able to check win`() {
        val game = Game()
        val player1 = Player(Person("a"))
        val player2 = Player(Person("b"))

        game.addPlayers(player1)
        game.addPlayers(player2)

        assertEquals(game.checkWin(), 0)
    }

    @Test
    fun `should be able to check win and update status`() {
        val game = Game()
        val player1 = Player(Person("a"))
        val player2 = Player(Person("b"))

        game.addPlayers(player1)
        game.addPlayers(player2)
        player2.updateGameScore(5)

        assertEquals(game.checkWin(), 1)
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

        assertEquals(game.checkWin(), 1)
    }
    @Test
    fun `should be able to start game`() {
        val game = Game()
        game.play()

        assertEquals(game.getStatus(),GameStatus.ACTIVE)
    }

}