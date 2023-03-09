package com.example.models

import com.example.error.InValidMoveException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CaromBoardTest {
    @Test
    fun `should give count of Red Coins`() {
        val count = CaromBoard().getRedCoinsCount()

        assertEquals(COUNT_OF_RED_COINS, count)
    }

    @Test
    fun `should give count of black Coins`() {
        val count = CaromBoard().getBlackCoinsCount()

        assertEquals(COUNT_OF_BLACK_COINS, count)
    }

    @Test
    fun `should give count of striker`() {
        val count = CaromBoard().getStrikerCount()

        assertEquals(COUNT_OF_STRIKER, count)
    }

    @Test
    fun `should update count of Red coins`() {
        val board = CaromBoard()
        val temp = 1

        board.updateRedCoinsCount(temp)

        assertEquals(COUNT_OF_RED_COINS + temp, board.getRedCoinsCount())
    }

    @Test
    fun `should update count of black coins`() {
        val board = CaromBoard()
        val temp = -1

        board.updateBlackCoinsCount(temp)

        assertEquals(COUNT_OF_BLACK_COINS + temp, board.getBlackCoinsCount())
    }

    @Test
    fun `should be able to check coins remaining`() {
        val board = CaromBoard()

        board.updateBlackCoinsCount(-9)
        board.updateRedCoinsCount(-1)

        assertEquals(true, board.isCoinsOver())
    }

    @Test
    fun `should throw exception for negative coin black count`() {
        val board = CaromBoard()

        assertThrows<InValidMoveException> { board.updateBlackCoinsCount(-10) }
    }

    @Test
    fun `should throw exception for negative coin Red count`() {
        val board = CaromBoard()

        assertThrows<InValidMoveException> { board.updateRedCoinsCount(-2) }
    }

}