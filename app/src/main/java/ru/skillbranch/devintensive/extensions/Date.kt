package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.utils.Utils
import java.text.SimpleDateFormat
import java.util.*

private const val SECOND = 1000L
private const val MINUTE = 60 * SECOND
private const val HOUR = 60 * MINUTE
private const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Long, units: TimeUnits = TimeUnits.SECOND): Date {
    val milliseconds = when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    return Date(this.time + milliseconds)
}

fun Date.humanizeDiff(date: Date = Date()): String {
    var dateDiff = date.add(-this.time / SECOND).time
    val isFuture = dateDiff < 0
    dateDiff = kotlin.math.abs(dateDiff)
    if (dateDiff % SECOND >= 500) dateDiff += SECOND //округление до ближайшей секунды
    if (dateDiff in 0..1 * SECOND) return "только что"
    val result = when (dateDiff) {
        in 2 * SECOND..45 * SECOND -> "несколько секунд"
        in 46 * SECOND..75 * SECOND -> "минуту"
        in 76 * SECOND..45 * MINUTE -> "${dateDiff / MINUTE} ${TimeUnits.MINUTE.plural(dateDiff / MINUTE)}"
        in 46 * MINUTE..75 * MINUTE -> "час"
        in 76 * MINUTE..22 * HOUR -> "${dateDiff / HOUR} ${TimeUnits.HOUR.plural(dateDiff / HOUR)}"
        in 22 * HOUR..26 * HOUR -> "день"
        in 26 * HOUR..360 * DAY -> "${dateDiff / DAY} ${TimeUnits.DAY.plural(dateDiff / DAY)}"
        else -> "год"
    }
    return if (isFuture) (if (dateDiff > 360 * DAY) "более чем " else "") + "через $result"
    else (if (dateDiff > 360 * DAY) "более года" else result) + " назад"

}

enum class TimeUnits : Utils.Pluralable {
    SECOND {
        override val words: Array<String> = arrayOf("секунд", "секунду", "секунды")
    },
    MINUTE {
        override val words: Array<String> = arrayOf("минут", "минуту", "минуты")
    },
    HOUR {
        override val words: Array<String> = arrayOf("часов", "час", "часа")
    },
    DAY {
        override val words: Array<String> = arrayOf("дней", "день", "дня")
    };
}