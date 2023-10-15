package com.example.query.controller

import com.example.query.entity.ChatMessage
import com.example.query.entity.RoomParticipant
import com.example.query.entity.RoomSummary
import com.example.query.queries.AllRoomsQuery
import com.example.query.queries.RoomMessagesQuery
import com.example.query.queries.RoomParticipantsQuery
import org.axonframework.messaging.responsetypes.MultipleInstancesResponseType
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("/v1/rooms")
class QueryController(private val queryGateway: QueryGateway) {

    @GetMapping
    fun listRooms(): CompletableFuture<List<RoomSummary>> {
        return queryGateway.query(
            AllRoomsQuery(),
            MultipleInstancesResponseType(RoomSummary::class.java)
        )
    }

    @GetMapping("{roomId}/participants")
    fun participantsInRoom(@PathVariable roomId: String): CompletableFuture<List<RoomParticipant>> {
        return queryGateway.query(
            RoomParticipantsQuery(roomId),
            MultipleInstancesResponseType(RoomParticipant::class.java)
        )
    }

    @GetMapping("{roomId}/messages")
    fun roomMessages(@PathVariable roomId: String): CompletableFuture<List<ChatMessage>> {
        return queryGateway.query(
            RoomMessagesQuery(roomId),
            MultipleInstancesResponseType(ChatMessage::class.java)
        )
    }
}