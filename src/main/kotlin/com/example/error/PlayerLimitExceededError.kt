package com.example.error

import com.example.models.PLAYERS_COUNT

class PlayerLimitExceededError: Error("only $PLAYERS_COUNT players are allowed")