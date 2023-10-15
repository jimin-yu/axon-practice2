package com.example.query.projection

import com.example.events.ParticipantJoinedRoomEvent
import com.example.events.ParticipantLeftRoomEvent
import com.example.query.entity.RoomParticipant
import com.example.query.queries.RoomParticipantsQuery
import com.example.query.repository.RoomParticipantRepository
import com.example.query.repository.RoomSummaryRepository
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
class RoomParticipantProjection(
    private val participantRepository: RoomParticipantRepository,
    private val roomSummaryRepository: RoomSummaryRepository
) {

    @EventHandler
    @Transactional
    fun on(evt: ParticipantJoinedRoomEvent){
        val participant = RoomParticipant(evt.roomId, evt.participant)
        participantRepository.save(participant)

        val roomSummary = roomSummaryRepository.findById(evt.roomId).get()
        roomSummary.participants += 1
        roomSummaryRepository.save(roomSummary)
    }

    @EventHandler
    @Transactional
    fun on(evt: ParticipantLeftRoomEvent) {
        participantRepository.deleteByParticipantAndRoomId(evt.participant, evt.roomId)

        val roomSummary = roomSummaryRepository.findById(evt.roomId).get()
        roomSummary.participants -= 1
        roomSummaryRepository.save(roomSummary)
    }

    @QueryHandler
    fun handle(query: RoomParticipantsQuery): List<RoomParticipant> {
        return participantRepository.findRoomParticipantsByRoomId(query.roomId)
    }
}