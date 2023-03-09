package com.example


import com.example.models.CaromBoard
import com.example.models.Game
import com.example.models.Player

fun main() {
    val board = CaromBoard()
    val game = Game(board)

    val player1 = Player("sanjeev")
    val player2 = Player("kumar")
    game.addPlayers(player1)
    game.addPlayers(player2)

    val ls = listOf("Multi strike", "Strike", "Red strike", "Defunct coin")
    for (element in ls) {
        game.play(element)
    }

    println(game.getWinner())
}


