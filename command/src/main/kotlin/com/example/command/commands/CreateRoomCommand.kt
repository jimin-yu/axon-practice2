package com.example.command.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateRoomCommand(@TargetAggregateIdentifier val roomId: String, val name: String)