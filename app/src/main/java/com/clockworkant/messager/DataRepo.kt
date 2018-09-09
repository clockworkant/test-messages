package com.clockworkant.messager

import com.beust.klaxon.Klaxon

interface DataRepo {
    fun getMessages(): List<Message>
    fun getUsers(): List<User>
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