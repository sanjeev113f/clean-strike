package com.example.models

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@MicronautTest
class GameTest
{
    private val game= Game()

    @Test
    fun `should be able set status for game` () {
        game.setStatus(GameStatus.ACTIVE)

        assertEquals(game.getStatus(),GameStatus.ACTIVE)
    }

    @Test
    fun `should be able set turn for players` () {

        val person = Person("sanjeev")
        val player = Player(person)
        val game = Game()
        game.setPlayers(player)

        assertEquals(game.getPlayers().person.name,"sanjeev")
    }

    @Test
    fun `should be able set moves for players` () {
        val moves = ArrayList<String>()
        moves.add("strike")
        game.setMoves(moves)

        assertEquals(game.getMoves(),moves)
    }

    @Test
    fun `should be able add players` () {
        val person = Person("sanjeev")
        val player = Player(person)
        game.setPlayers(player)
        assertEquals(game.getPlayers(),player)
    }

}