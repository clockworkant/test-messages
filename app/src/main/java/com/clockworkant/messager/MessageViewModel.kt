package com.clockworkant.messager

data class MessageViewModel(
        val messageId: Long,
        val isAdminUser: Boolean,
        val name: String,
        val userImageUrl: String,
        val message: String,
        val attachments: List<Attachment>
)