package com.clockworkant.messages

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

private const val adminUser = 1
private const val otherUser = 2

class MessagesAdapter() : RecyclerView.Adapter<MessageViewHolder>() {
    private var onLastItemShown: () -> Unit = {}
    private var onMessageSelected: (Long) -> Unit = {}
    private var onAttachmentSelected: (messageId: Long, attachmentId: String) -> Unit = { _,_ ->}

    init {
        setHasStableIds(true)
    }

    private val items: MutableList<MessageViewModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return when (viewType) {
            adminUser ->
                MessageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.message_item_admin, parent, false), onMessageSelected, onAttachmentSelected)
            else ->
                MessageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false), onMessageSelected, onAttachmentSelected)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(items[position])
        if (position == items.size - 1) {
            onLastItemShown()
        }
    }

    override fun getItemId(position: Int): Long {
        return items[position].messageId
    }

    private fun getItemIndex(messageId: Long): Int {
        return items.indexOfFirst { it.messageId == messageId }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].isAdminUser) {
            adminUser
        } else {
            otherUser
        }
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

    fun setOnMessageSelected(callback: (Long) -> Unit) {
        onMessageSelected = callback
    }

    fun remove(messageId: Long) {
        val itemIndex = getItemIndex(messageId)
        if (itemIndex != -1) {
            items.removeAt(itemIndex)
            notifyItemRemoved(itemIndex)
        }
    }

    fun setOnAttachmentSelected(callback: (messageId: Long, attachmentId: String) -> Unit) {
        onAttachmentSelected = callback
    }

    fun removeAttachment(messageId: Long, attachmentId: String) {
        items.find {
            it.messageId == messageId
        }?.let {
            val attachment = it.attachments.find { attachment -> attachment.id == attachmentId }
            it.attachments.remove(attachment)
            notifyItemChanged(getItemIndex(messageId))
        }
    }
}