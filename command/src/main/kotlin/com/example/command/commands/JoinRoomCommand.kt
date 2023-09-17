package com.example.command.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class JoinRoomCommand(@TargetAggregateIdentifier val roomId: String, val participant: String)