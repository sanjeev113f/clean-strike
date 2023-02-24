package com.example.models

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@MicronautTest
class GameTest {
    private val game = Game()

    @Test
    fun `should be able set status for game`() {
        game.setStatus(GameStatus.ACTIVE)

        assertEquals(game.getStatus(), GameStatus.ACTIVE)
    }

    @Test
    fun `should be able set turn for players`() {

        val person = Person("sanjeev")
        val player = Player(person)
        val game = Game()
        game.addPlayers(player)

        assertEquals(game.getPlayers().person.name, "sanjeev")
    }

    @Test
    fun `should be able set moves for players`() {
        val moves = ArrayList<String>()
        moves.add("strike")
        game.setMoves(moves)

        assertEquals(game.getMoves(), moves)
    }

    @Test
    fun `should be able add players`() {
        val person = Person("sanjeev")
        val player = Player(person)
        game.addPlayers(player)
        assertEquals(game.getPlayers(), player)
    }

    @Test
    fun `should be able to check win`() {
        val player1 = Player(Person("a"))
        val player2 = Player(Person("b"))
        game.addPlayers(player1)
        game.addPlayers(player2)

        assertEquals(game.checkWin(), 0)
    }

    @Test
    fun `should be able to check win with updated score`() {
        val player1 = Player(Person("a"))
        val player2 = Player(Person("b"))
        game.addPlayers(player1)
        game.addPlayers(player2)
        player1.updateGameScore(10)
        player2.updateGameScore(2)
        val game = game.checkWin()

        assertEquals(game, 1)
    }

//    @Test
//    fun `should be able to start game`() {
//        val player = game.startGame()
//
//        assertEquals(player.person.name, "")
//    }

}