package com.example.command.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class PostMessageCommand(@TargetAggregateIdentifier val roomId: String, val participant: String, val message: String)