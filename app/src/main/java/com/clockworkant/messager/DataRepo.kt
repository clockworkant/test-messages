package com.clockworkant.messager

import com.beust.klaxon.Klaxon
import kotlin.math.min

interface DataRepo {
    fun getMessagesAfter(lastItemID: Long, numberOfMessagesToFetch: Int): List<Message>
    fun getUsers(): List<User>
}

class DataRepoJsonImpl(val json: String) : DataRepo {
    private val dataWrapper: DataWrapper

    init {
        dataWrapper = Klaxon().parse<DataWrapper>(json)!!
    }

    override fun getUsers(): List<User> = dataWrapper.users

    override fun getMessagesAfter(lastItemID: Long, numberOfMessagesToFetch: Int): List<Message> {
        var fromIndex = dataWrapper.messages.indexOfLast { it.id == lastItemID }
        if (fromIndex == -1) {
            fromIndex = 0
        } else {
            //increment so we get the next data range
            fromIndex++
        }

        return dataWrapper.messages.subList(
                fromIndex,
                min(fromIndex + numberOfMessagesToFetch, dataWrapper.messages.size) //prevent going out of bounds
        )
    }

    data class DataWrapper(
            val messages: List<Message>,
            val users: List<User>
    )
}