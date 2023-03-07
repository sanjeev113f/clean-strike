package com.example.models

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `should be able to create a player`() {
        val person = Person("aa")
        val player = Player(person)

        assertEquals(player.person, person)
    }

    @Test
    fun `should give black coin count for player`() {
        val person = Person("aa")
        val player = Player(person)

        assertEquals(player.getBlackCoinCount(), 0)
    }

    @Test
    fun `should give red coin count for player`() {
        val person = Person("aa")
        val player = Player(person)

        assertEquals(player.getRedCoinCount(), 0)
    }
    @Test
    fun `should add in red coin count for player`() {
        val person = Person("aa")
        val player = Player(person)

        player.addRedCoin(5)

        assertEquals(player.getRedCoinCount(), 5)
    }

    @Test
    fun `should give gameScore`() {
        val person = Person("aa")
        val player = Player(person)

        assertEquals(player.getGameScore(), 0)
    }

    @Test
    fun `should update gameScore`() {
        val person = Person("aa")
        val player = Player(person)

        player.updateGameScore(10)

        assertEquals(player.getGameScore(), 10)
    }

    @Test
    fun `should give foulCount`() {
        val person = Person("aa")
        val player = Player(person)

        assertEquals(player.getFoulCount(), 0)
    }

    @Test
    fun `should update foulCount`() {
        val person = Person("aa")
        val player = Player(person)

        player.updateFoulCount(10)

        assertEquals(player.getFoulCount(), 10)
    }

    @Test
    fun `should give threeSuccessiveTurnPoint`() {
        val person = Person("aa")
        val player = Player(person)

        assertEquals(player.getThreeSuccessiveTurnEarning(), 0)
    }

    @Test
    fun `should update threeSuccessiveTurnPoint`() {
        val person = Person("aa")
        val player = Player(person)

        player.updateSuccessiveTurnEarning(12)

        assertEquals(player.getThreeSuccessiveTurnEarning(), 12)
    }

}