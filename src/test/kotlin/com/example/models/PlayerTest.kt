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
}