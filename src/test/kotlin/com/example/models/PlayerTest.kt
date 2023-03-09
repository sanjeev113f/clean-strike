package com.example.models

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `should be able to create a player`() {
        val player = Player("aa")

        assertEquals("aa", player.getName())
    }

    @Test
    fun `should give black coin count for player`() {
        val player = Player("aa")
        val expected = MIN_COUNT

        assertEquals(expected, player.getBlackCoinCount())
    }

    @Test
    fun `should give red coin count for player`() {
        val player = Player("aa")
        val expected = MIN_COUNT

        assertEquals(expected, player.getRedCoinCount())
    }

    @Test
    fun `should add in red coin count for player`() {
        val player = Player("aa")
        val expected = 5 + MIN_COUNT

        player.addRedCoin(5)

        assertEquals(expected, player.getRedCoinCount())
    }

    @Test
    fun `should add in black coin count for player`() {
        val player = Player("aa")
        val expected = MIN_COUNT + 10

        player.addBlackCoin(10)

        assertEquals(expected, player.getBlackCoinCount())
    }

    @Test
    fun `should give gameScore`() {
        val player = Player("aa")
        val expected = MIN_GAME_SCORE

        assertEquals(expected, player.getGameScore())
    }

    @Test
    fun `should update gameScore`() {
        val player = Player("aa")
        val expected = MIN_GAME_SCORE + 10

        player.updateGameScore(10)

        assertEquals(expected, player.getGameScore())
    }

    @Test
    fun `should give foulCount`() {
        val player = Player("aa")
        val expected = MIN_COUNT

        assertEquals(expected, player.getFoulCount())
    }

    @Test
    fun `should update foulCount`() {
        val player = Player("aa")
        val expected = MIN_COUNT + 10

        player.updateFoulCount(10)

        assertEquals(expected, player.getFoulCount())
    }

    @Test
    fun `should give threeSuccessiveTurnPoint`() {
        val player = Player("aa")
        val expected = MIN_COUNT

        assertEquals(expected, player.getThreeSuccessiveTurnsCoins())
    }

    @Test
    fun `should update threeSuccessiveTurnPoint`() {
        val player = Player("aa")
        val expected = MIN_COUNT + 12

        player.updateThreeSuccessiveTurnsCoins(12)

        assertEquals(expected, player.getThreeSuccessiveTurnsCoins())
    }

}