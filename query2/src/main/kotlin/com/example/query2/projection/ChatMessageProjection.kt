package com.example.query2.projection

import com.example.events.MessagePostedEvent
import com.example.query2.entity.ChatMessage
import com.example.query2.queries.RoomMessagesQuery
import com.example.query2.repository.ChatMessageRepository
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.axonframework.eventhandling.Timestamp
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component
import java.time.Instant

@Component
//@ProcessingGroup("group2")
class ChatMessageProjection(private val repository: ChatMessageRepository) {

    // Read db 업데이트
    @EventHandler
    fun on(evt: MessagePostedEvent, @Timestamp timestamp: Instant) {
        val chatMessage = ChatMessage(evt.participant, evt.roomId, evt.message, timestamp.toEpochMilli())
        repository.save(chatMessage)
    }


    // Read db에서 조회
    @QueryHandler
    fun handle(query: RoomMessagesQuery): List<ChatMessage> {
        return repository.findAllByRoomIdOrderByTimestamp(query.roomId)
    }
}