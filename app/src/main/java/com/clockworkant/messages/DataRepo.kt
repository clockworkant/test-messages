package com.clockworkant.messages

import android.content.Context
import com.beust.klaxon.Klaxon
import com.clockworkant.messages.room.MessagesDatabase

interface DataRepo {
    fun getMessagesAfter(lastItemID: Long, numberOfMessagesToFetch: Int): List<Message>
    fun getUsers(): List<User>
    fun deleteMessage(messageId: Long)
    fun deleteAttachment(attachmentId: String)
}

class DataRepoRoomImpl(val context: Context) : DataRepo {
    private val db = MessagesDatabase.getInstance(context)

    init {
        db?.let {
            if (it.userDao().getAllUsers().isEmpty()) {
                populateDatabaseFromJson(it)
            }
        }
    }

    private fun populateDatabaseFromJson(it: MessagesDatabase) {
        val jsonString = context.assets.open("data.json").bufferedReader().use {
            it.readText()
        }
        val dataWrapper = Klaxon().parse<DataWrapper>(jsonString)!!
        it.userDao().insert(dataWrapper.users)
        it.messagesDao().insert(dataWrapper.messages)
    }

    override fun getMessagesAfter(lastItemID: Long, numberOfMessagesToFetch: Int): List<Message> {
        if (db == null) return emptyList()
        return db.messagesDao().getMessagesAfter(lastItemID, numberOfMessagesToFetch)
    }

    override fun getUsers(): List<User> {
        if (db == null) return emptyList()
        return db.userDao().getAllUsers()
    }

    override fun deleteMessage(messageId: Long) {
        db?.let {
            db.messagesDao().delete(db.messagesDao().getMessageForId(messageId))
        }
    }

    override fun deleteAttachment(attachmentId: String) {
        //TODO delete attachment
    }

    private data class DataWrapper(
            val messages: List<Message>,
            val users: List<User>
    )
}