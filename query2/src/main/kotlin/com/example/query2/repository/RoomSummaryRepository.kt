package com.example.query2.repository

import com.example.query2.entity.RoomSummary
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoomSummaryRepository : JpaRepository<RoomSummary, Long> {
}