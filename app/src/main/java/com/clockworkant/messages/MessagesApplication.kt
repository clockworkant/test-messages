package com.clockworkant.messages

import android.app.Application
import com.facebook.stetho.Stetho

class MessagesApplication : Application() {

    /**
     * TODO in the future this should be using Dependency injection, something like Koin
     */
    companion object {
        lateinit var dataRepo: DataRepo
    }

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)

        dataRepo = DataRepoRoomImpl(applicationContext)
    }
}