package com.example.query.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class RoomParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    var roomId: String = ""
    var participant: String = ""
    constructor()
    constructor(roomId: String, participant: String) {
        this.roomId = roomId
        this.participant = participant
    }
}