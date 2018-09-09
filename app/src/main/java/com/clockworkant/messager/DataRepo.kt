package com.clockworkant.messager

import android.util.Log
import com.beust.klaxon.Klaxon

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

class DataRepoJsonImpl(val json: String) : DataRepo {
    val dataWrapper: DataWrapper

    init {
        dataWrapper = Klaxon().parse<DataWrapper>(json)!!
    }

    override fun getUsers(): List<User> = dataWrapper.users

    override fun getMessages(): List<Message> = dataWrapper.messages

    data class DataWrapper(
            val messages: List<Message>,
            val users: List<User>
    )
}