package com.example.query.controller

import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/rooms")
class QueryController(private val queryGateway: QueryGateway) {
    @PostMapping
    fun createChatRoom(): String {
        return "ok"
    }
}