package com.example.query.repository

import com.example.query.entity.RoomParticipant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoomParticipantRepository : JpaRepository<RoomParticipant, Long> {
    fun findRoomParticipantsByRoomId(roomId: String): List<RoomParticipant>
    fun deleteByParticipantAndRoomId(participant: String, roomId: String)
}