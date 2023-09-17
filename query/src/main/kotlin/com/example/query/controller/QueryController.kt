package com.example.query.controller

import com.example.query.entity.RoomSummary
import com.example.query.queries.AllRoomsQuery
import org.axonframework.messaging.responsetypes.MultipleInstancesResponseType
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("/v1/rooms")
class QueryController(private val queryGateway: QueryGateway) {
    @GetMapping
    fun createChatRoom(): CompletableFuture<List<RoomSummary>?> {
        return queryGateway.query(AllRoomsQuery(), MultipleInstancesResponseType(RoomSummary::class.java))
    }
}