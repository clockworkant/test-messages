package com.clockworkant.messager

data class Message(
        val id: Long,
        val userId: Long,
        val content: String,
        val attachments: List<Attachment> = emptyList()
)

data class Attachment(
        val id: String,
        val title: String,
        val url: String,
        val thumbnailUrl: String
)

data class User(
        val id: Long,
        val name: String,
        val avatarId: String
)