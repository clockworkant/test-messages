package com.clockworkant.messages

data class MessageViewModel(
        val messageId: Long,
        val isAdminUser: Boolean,
        val name: String,
        val userImageUrl: String,
        val message: String,
        val attachments: MutableList<Attachment>
)