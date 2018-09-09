package com.clockworkant.messager

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

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