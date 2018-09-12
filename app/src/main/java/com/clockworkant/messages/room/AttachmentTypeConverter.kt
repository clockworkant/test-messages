package com.clockworkant.messages.room

import android.arch.persistence.room.TypeConverter
import com.beust.klaxon.Klaxon
import com.clockworkant.messages.Attachment
import java.util.Collections.emptyList

class AttachmentTypeConverter {

    @TypeConverter
    fun stringToAttachmentList(data: String?): List<Attachment> {
        return data?.let {  Klaxon().parse<AttachmentWrapper>(data)?.attachments } ?: return emptyList()
    }

    @TypeConverter
    fun attachmentListToString(attachments: List<Attachment>?): String {
        return Klaxon().toJsonString(AttachmentWrapper(attachments?: emptyList()))
    }

    private data class AttachmentWrapper(val attachments:List<Attachment>)
}