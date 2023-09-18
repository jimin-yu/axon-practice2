package com.example.query2.repository

import com.example.query2.entity.ChatMessage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatMessageRepository : JpaRepository<ChatMessage, Long> {
    fun findAllByRoomIdOrderByTimestamp(roomId: String): List<ChatMessage>
}