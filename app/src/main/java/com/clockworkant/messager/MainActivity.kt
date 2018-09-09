package com.clockworkant.messager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.message_item.view.*

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

data class MessageViewModel(
        val isAdminUser: Boolean,
        val name: String,
        val userImageUrl: String,
        val message: String,
        val attachments: List<Attachment>?
)

class MessagesAdapter : RecyclerView.Adapter<MessageViewHolder>() {

    private val items: MutableList<MessageViewModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder =
            MessageViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
            )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addMessages(messageViewModels: List<MessageViewModel>) {
        items.addAll(messageViewModels)
        notifyDataSetChanged()
    }
}

class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(messageViewModel: MessageViewModel) {
        itemView.message_content.text = messageViewModel.message
        itemView.user_name.text = messageViewModel.name
//        itemView.user_avatar
    }

}
