package ru.skillbranch.devintensive.extensions

fun String.truncate(count: Int = 16): String {
    return if (count < this.trim().length) {
        (this.substring(0, count).trim() + "...")
    } else {
        this.trim()
    }
}