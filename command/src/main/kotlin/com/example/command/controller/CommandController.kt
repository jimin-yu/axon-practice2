package com.example.command.controller

import com.example.command.commands.CreateRoomCommand
import com.example.command.commands.JoinRoomCommand
import com.example.command.commands.LeaveRoomCommand
import com.example.command.commands.PostMessageCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.*
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

    @PostMapping("/{roomId}/participants")
    fun joinChatRoom(@PathVariable roomId: String, @RequestBody joinRoomRequest: JoinRoomRequest): String {
        println(roomId)
        println(joinRoomRequest)
        val command = JoinRoomCommand(roomId, joinRoomRequest.name)
        println(command)
        commandGateway.sendAndWait<Any>(command)
        return "ok"
    }

    @PostMapping("/{roomId}/messages")
    fun postMessage(@PathVariable roomId: String, @RequestBody postMessageRequest: PostMessageRequest): String {
        val command = PostMessageCommand(roomId, postMessageRequest.participant, postMessageRequest.message)
        commandGateway.sendAndWait<Any>(command)
        return "ok"
    }

    @DeleteMapping("/{roomId}/participants")
    fun leaveChatRoom(@PathVariable roomId: String, @RequestBody leaveRoomRequest: LeaveRoomRequest): String {
        val command = LeaveRoomCommand(roomId, leaveRoomRequest.name)
        commandGateway.sendAndWait<Any>(command)
        return "ok"
    }
}