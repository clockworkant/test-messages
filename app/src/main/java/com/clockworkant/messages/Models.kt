package com.clockworkant.messages

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.clockworkant.messages.room.AttachmentTypeConverter

@Entity(tableName = "messages")
data class Message(
        @PrimaryKey
        val id: Long,
        val userId: Long,
        val content: String,
        @TypeConverters(AttachmentTypeConverter::class)
        val attachments: List<Attachment> = emptyList()
)

data class Attachment(
        val id: String,
        val title: String,
        val url: String,
        val thumbnailUrl: String
)

@Entity(tableName = "users")
data class User(
        @PrimaryKey
        val id: Long,
        val name: String,
        val avatarId: String
)