package com.example.events

data class MessagePostedEvent(val roomId: String, val participant: String, val message: String)