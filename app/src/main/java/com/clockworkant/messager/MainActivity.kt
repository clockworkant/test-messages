package com.clockworkant.messager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

private const val NumberOfMessagesToFetch = 20

class MainActivity : AppCompatActivity() {
    private val messagesAdapter: MessagesAdapter = MessagesAdapter()
    private lateinit var dataRepo: DataRepo
    private lateinit var users: List<User>
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataRepo = MessagesApplication.dataRepo
        users = dataRepo.getUsers()

        val viewManager = LinearLayoutManager(this)
        messages_recyclerview.apply {
            layoutManager = viewManager
            adapter = messagesAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                }
            })
        }

        messagesAdapter.setOnLastItemShown {
            messages_recyclerview.post {fetchAndDisplayMessages()}
        }

        fetchAndDisplayMessages()
    }

    private fun fetchAndDisplayMessages() {
        isLoading = true
        val lastItemID = messagesAdapter.getLastItemID()

        val messageViewModels = dataRepo.getMessagesAfter(lastItemID, NumberOfMessagesToFetch).map {
            MessageConverter.toViewModel(it, users.first { user -> it.userId == user.id })
        }

        messagesAdapter.addMessages(messageViewModels)
        isLoading = false
    }
}

