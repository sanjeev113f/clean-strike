package com.example.error

import com.example.models.PLAYERS_COUNT

class PlayerLimitExceededException: Exception("only $PLAYERS_COUNT players are allowed")