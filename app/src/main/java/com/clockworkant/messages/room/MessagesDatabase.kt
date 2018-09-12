package com.clockworkant.messages.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.clockworkant.messages.Message
import com.clockworkant.messages.User

@Database(entities = [(Message::class), (User::class)], version = 1, exportSchema = false)
@TypeConverters(AttachmentTypeConverter::class)
abstract class MessagesDatabase : RoomDatabase() {
    abstract fun messagesDao(): MessagesDao
    abstract fun userDao(): UsersDao

    companion object {
        private var INSTANCE: MessagesDatabase? = null

        fun getInstance(context: Context): MessagesDatabase? {
            if (INSTANCE == null) {
                synchronized(MessagesDatabase::class) {
                    INSTANCE = Room
                            .databaseBuilder(context.applicationContext, MessagesDatabase::class.java, "messages.db")
                            .allowMainThreadQueries()//TODO STOPSHIP - IMPLEMENT THREADING!!!!
                            .build()
                }
            }
            return INSTANCE
        }
    }
}