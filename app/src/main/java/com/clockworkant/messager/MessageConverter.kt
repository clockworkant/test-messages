package com.clockworkant.messager

private const val adminUserId = 0L

object MessageConverter {

    fun toViewModel(message: Message, user: User): MessageViewModel {
        return MessageViewModel(
                message.id,
                user.id == adminUserId,
                user.name,
                user.avatarId,
                message.content,
                message.attachments
        )
    }
}
