package com.example.query.projection

import com.example.events.RoomCreatedEvent
import com.example.query.entity.RoomSummary
import com.example.query.queries.AllRoomsQuery
import com.example.query.repository.RoomSummaryRepository
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class RoomSummaryProjection(private val repository: RoomSummaryRepository) {

    @EventHandler
    fun on(evt: RoomCreatedEvent) {
        val summary = RoomSummary(evt.roomId, evt.name)
        repository.save(summary)
    }

    @QueryHandler
    fun handle(query: AllRoomsQuery): List<RoomSummary> {
        return repository.findAll()
    }
}