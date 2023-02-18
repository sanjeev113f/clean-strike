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

}