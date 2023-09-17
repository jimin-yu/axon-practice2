package com.example.command.controller

import com.example.command.commands.CreateRoomCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/v1/rooms")
class CommandController(private val commandGateway: CommandGateway) {
    @PostMapping
    fun createChatRoom(@RequestBody createRoomReqeust: CreateRoomReqeust): String {
        val roomId = UUID.randomUUID().toString()
        val command = CreateRoomCommand(roomId, createRoomReqeust.name)
        commandGateway.sendAndWait<Any>(command)
        return "ok"
    }
}