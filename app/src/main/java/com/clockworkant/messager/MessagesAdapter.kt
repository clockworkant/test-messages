package com.clockworkant.messager

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class MessagesAdapter : RecyclerView.Adapter<MessageViewHolder>() {
    private var onLastItemShown: () -> Unit = {}

    init {
        setHasStableIds(true)
    }

    private val items: MutableList<MessageViewModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder =
            MessageViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
            )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(items[position])
        if(position == items.size - 1) {
            onLastItemShown()
        }
    }

    override fun getItemId(position: Int): Long {
        return items[position].messageId
    }

    fun addMessages(messageViewModels: List<MessageViewModel>) {
        items.addAll(messageViewModels)
        notifyItemRangeChanged(items.size - messageViewModels.size, messageViewModels.size)
    }

    fun getLastItemID(): Long {
        return if (items.isEmpty()) {
            -1L;
        } else {
            items.last().messageId
        }
    }

    fun setOnLastItemShown(callback: () -> Unit) {
        onLastItemShown = callback
    }
}