package com.clockworkant.messages

private const val adminUserId = 1L

object MessageConverter {

    fun toViewModel(message: Message, user: User): MessageViewModel {
        val isAdminUser = user.id == adminUserId

        val username = if(isAdminUser) {
            "Me" //todo this should really come from the string resources
        } else {
            user.name
        }
        return MessageViewModel(
                message.id,
                isAdminUser,
                username,
                user.avatarId,
                message.content,
                message.attachments
        )
    }
}
