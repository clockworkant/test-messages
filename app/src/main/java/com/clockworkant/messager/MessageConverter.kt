package com.clockworkant.messager

object MessageConverter {
    fun toViewModel(message: Message, user: User): MessageViewModel {
        return MessageViewModel(
                user.id == 0L,
                user.name,
                user.avatarId,
                message.content,
                message.attachments
        )
    }
}