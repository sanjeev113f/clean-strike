package com.example.models

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MovesTest {
    @Test
    fun `should be able to check a move`() {
        val moves = Moves()
        val expected = "Strike"

        assertEquals(expected, moves.getValidMoves()[0])
    }
}