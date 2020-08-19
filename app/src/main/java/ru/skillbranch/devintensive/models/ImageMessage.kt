package ru.skillbranch.devintensive.models

import java.util.*

class ImageMessage(
    id: String,
    from: User?,
    chat: Chat,
    isIncoming: Boolean = false,
    date: Date = Date(),
    var imageUrl: String?
) : BaseMessage(id, from, chat, isIncoming, date) {
    override fun formatMessage(): String {
        return "${from?.firstName} " + (if (isIncoming) "получил " else "отправил ") + "изображение"
    }
}