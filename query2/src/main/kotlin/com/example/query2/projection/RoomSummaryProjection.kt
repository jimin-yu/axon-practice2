package com.example.query2.projection

import com.example.events.RoomCreatedEvent
import com.example.query2.entity.RoomSummary
import com.example.query2.queries.AllRoomsQuery
import com.example.query2.repository.RoomSummaryRepository
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
//@ProcessingGroup("group1")
class RoomSummaryProjection(private val repository: RoomSummaryRepository) {

    @EventHandler
    fun on(evt: RoomCreatedEvent) {
        System.out.println("!!!!!!!!!!!!!!!!!!!! EVENT HANDLER 22222 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
        val summary = RoomSummary(evt.roomId, evt.name)
        repository.save(summary)
    }

    @QueryHandler
    fun handle(query: AllRoomsQuery): List<RoomSummary> {
        return repository.findAll()
    }
}