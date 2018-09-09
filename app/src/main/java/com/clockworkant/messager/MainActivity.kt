package com.clockworkant.messager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val messagesAdapter: MessagesAdapter = MessagesAdapter()
    private lateinit var dataRepo: DataRepo
    private lateinit var users: List<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataRepo = MessagesApplication.dataRepo
        users = dataRepo.getUsers()

        val viewManager = LinearLayoutManager(this)

        messages_recyclerview.apply {
            layoutManager = viewManager
            adapter = messagesAdapter
        }
    }

    override fun onResume() {
        super.onResume()

        messagesAdapter.addMessages(
                dataRepo.getMessages().map {
                    MessageConverter.toViewModel(it, users.first { user -> it.userId == user.id })
                }
        )
    }
}

