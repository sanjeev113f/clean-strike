package com.example.models

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@MicronautTest
class GameTest
{

    @Test
    fun `should be able set status for game` () {
        val game= Game()
        game.setStatus(GameStatus.ACTIVE)

        assertEquals(game.getStatus(),GameStatus.ACTIVE)
    }

    @Test
    fun `should be able set turn for players` () {
        val game= Game()
        val person = Person("sanjeev")
        game.setPlayerTurn(Player(person))

        assertEquals(game.getTurnPlayer().person.name,"sanjeev")
    }

    @Test
    fun `should be able set moves for players` () {
        val game= Game()
        val moves = ArrayList<String>()
        moves.add("strike")
        game.setMoves(moves)

        assertEquals(game.getMoves(),moves)
    }


}