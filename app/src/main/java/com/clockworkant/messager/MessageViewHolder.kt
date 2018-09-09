package com.clockworkant.messager

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.message_item.view.*

class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(messageViewModel: MessageViewModel) {
        itemView.message_content.text = messageViewModel.message
        itemView.user_name.text = messageViewModel.name
//        itemView.user_avatar
    }

}