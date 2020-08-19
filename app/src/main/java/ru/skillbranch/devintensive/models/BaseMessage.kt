package ru.skillbranch.devintensive.models

import java.util.*


abstract class BaseMessage(
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncoming: Boolean = false,
    val date: Date = Date()
) {

    abstract fun formatMessage(): String

    companion object AbstractFactory {
        var lastId = -1;
        fun makeMessage(
            from: User,
            chat: Chat,
            date: Date,
            type: String,
            payload: String,
            isIncoming: Boolean = false
        ): BaseMessage {
            return when (type) {
                "image" -> ImageMessage("${++lastId}", from, chat, isIncoming, date, payload)
                else -> TextMessage("${++lastId}", from, chat, isIncoming, date, payload)
            }
        }
    }
}