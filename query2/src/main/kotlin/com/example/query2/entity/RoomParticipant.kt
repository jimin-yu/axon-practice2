package com.example.query2.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class RoomParticipant {
    @Id
    var id: Long = 0
    var roomId: String = ""
    var participant: String = ""
    constructor()
    constructor(roomId: String, participant: String) {
        this.roomId = roomId
        this.participant = participant
    }
}