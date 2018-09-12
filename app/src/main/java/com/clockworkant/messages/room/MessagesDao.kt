package com.clockworkant.messages.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.clockworkant.messages.Message

@Dao
interface MessagesDao {

    @Insert
    fun insert(messages: List<Message>)

    @Delete
    fun delete(message: Message)

    @Query("SELECT * FROM messages")
    fun getAllMessages(): List<Message>

    @Query("SELECT * FROM messages WHERE id > :id LIMIT :count")
    fun getMessagesAfter(id: Long, count: Int): List<Message>

    @Query("SELECT * FROM messages WHERE id == :messageId")
    fun getMessageForId(messageId: Long): Message

}