package com.clockworkant.messager

import android.app.Application

class MessagesApplication : Application() {

    /**
     * TODO in the future this should be using Dependency injection, something like Koin
     */
    companion object {
        lateinit var dataRepo: DataRepo
    }

    override fun onCreate() {
        super.onCreate()
        dataRepo = DataRepoJsonImpl(
                assets.open("data.json").bufferedReader().use {
                    it.readText()
                }
        )
    }
}