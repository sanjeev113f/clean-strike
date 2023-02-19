package com.example.models

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CaromBoardTest
{
    @Test
    fun `should give count of Red Coins`() {
        val count = CaromBoard().getRedCoins()

        assertEquals(count, 1)
    }

    @Test
    fun `should give count of black Coins`() {
        val count = CaromBoard().getBlackCoins()

        assertEquals(count, 9)
    }
}