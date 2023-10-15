package com.example.query.repository

import com.example.query.entity.ChatMessage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatMessageRepository : JpaRepository<ChatMessage, Long> {
    fun findAllByRoomIdOrderByTimestamp(roomId: String): List<ChatMessage>
}