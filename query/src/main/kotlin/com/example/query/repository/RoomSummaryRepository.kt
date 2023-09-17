package com.example.query.repository

import com.example.query.entity.RoomSummary
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoomSummaryRepository : JpaRepository<RoomSummary, Long> {
}