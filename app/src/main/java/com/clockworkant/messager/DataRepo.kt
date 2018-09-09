package com.clockworkant.messager

interface DataRepo {
    fun getMessages(): List<Message>
    fun getUsers(): List<User>
}

class DataRepoMemoryImpl : DataRepo {
    override fun getMessages(): List<Message> = listOf<Message>(
            Message(1, 1, "This is a message", emptyList()),
            Message(1, 1, "This is a message", emptyList()),
            Message(1, 2, "This is a message", emptyList()),
            Message(1, 1, "This is a message", emptyList()),
            Message(1, 2, "This is a message", emptyList()),
            Message(1, 1, "This is a message", emptyList())
    )


    override fun getUsers(): List<User> = listOf(
            User(1, "John", ""),
            User(2, "Mark", "")
    )
}