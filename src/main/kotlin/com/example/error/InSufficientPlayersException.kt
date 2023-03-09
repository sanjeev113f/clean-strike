package com.example.error

import com.example.models.PLAYERS_COUNT

class InSufficientPlayersException(numberOfPlayers: Int) :
    Exception("Please Add ${PLAYERS_COUNT - numberOfPlayers} more players to Play game")