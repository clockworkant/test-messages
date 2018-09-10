package com.clockworkant.messages

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.include_attachment_view.view.*
import kotlinx.android.synthetic.main.include_body.view.*
import kotlinx.android.synthetic.main.include_title.view.*
import kotlinx.android.synthetic.main.message_item.view.*

class MessageViewHolder(view: View, val onMessageSelected: (Long) -> Unit) : RecyclerView.ViewHolder(view) {
    private val inflater: LayoutInflater = LayoutInflater.from(view.context)

    fun bind(messageViewModel: MessageViewModel) {
        itemView.user_name.text = "${messageViewModel.messageId} : ${messageViewModel.name}" //Todo remove messageId from name (used for visual testing)
        itemView.message_content.text = messageViewModel.message

        if (!messageViewModel.isAdminUser) {
            Glide.with(itemView)
                    .load(messageViewModel.userImageUrl)
                    .into(itemView.user_avatar)
        }

        itemView.cardView.setOnClickListener{
            onMessageSelected(messageViewModel.messageId)
        }

        bindAttachments(messageViewModel)
    }

    private fun bindAttachments(messageViewModel: MessageViewModel) {
        itemView.attachments.removeAllViews()
        messageViewModel.attachments.forEach {
            val attachmentView = inflater.inflate(R.layout.include_attachment_view, itemView.attachments, false)
            attachmentView.attachment_name.text = it.title
            Glide.with(itemView).load(it.thumbnailUrl).into(attachmentView.attachment_image)

            itemView.attachments.addView(attachmentView)
        }
    }

}