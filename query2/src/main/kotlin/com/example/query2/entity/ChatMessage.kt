package com.example.query2.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var timestamp: Long = 0
    var roomId: String? = null
    var message: String? = null
    var participant: String? = null

    constructor()

    constructor(roomId: String?, participant: String?, message: String?, timestamp: Long) {
        this.participant = participant
        this.roomId = roomId
        this.message = message
        this.timestamp = timestamp
    }
}