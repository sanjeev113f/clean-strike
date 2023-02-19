package com.example.models

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PlayerTest
{
    @Test
    fun `should be able to create a player`() {
        val person = Person("aa")
        val player = Player(person)

        assertEquals(player.person, person)
    }

    @Test
    fun `should give gameScore`() {
        val person = Person("aa")
        val player = Player(person)

        assertEquals(player.getGameScore(), 0)
    }
}