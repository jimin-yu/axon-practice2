package com.example.command.controller

data class CreateRoomReqeust(val name:String)

data class JoinRoomRequest(val name:String)

data class PostMessageRequest(val participant:String, val message:String)

data class LeaveRoomRequest(val name:String)