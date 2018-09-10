package com.clockworkant.messages

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
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

        messagesAdapter.setOnMessageSelected { messageId: Long ->
            AlertDialog.Builder(this)
                    .setTitle("Delete this message?")
                    .setMessage("Are you sure you want to delete this message?")
                    .setPositiveButton("OK") { _, _ ->
                        dataRepo.deleteMessage(messageId)
                        messagesAdapter.remove(messageId)
                    }
                    .setNegativeButton("Cancel", null)
                    .show()
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

