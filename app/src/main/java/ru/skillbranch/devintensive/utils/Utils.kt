package ru.skillbranch.devintensive.utils

object Utils {
    private val dictionary = mapOf(
        "а" to "a", "б" to "b", "в" to "v", "г" to "g",
        "д" to "d", "е" to "e", "ё" to "e", "ж" to "zh", "з" to "z", "и" to "i",
        "й" to "i", "к" to "k", "л" to "l", "м" to "m", "н" to "n", "о" to "o",
        "п" to "p", "р" to "r", "с" to "s", "т" to "t", "у" to "u", "ф" to "f",
        "х" to "h", "ц" to "c", "ч" to "ch", "ш" to "sh", "щ" to "sh'", "ъ" to "",
        "ы" to "i", "ь" to "", "э" to "e", "ю" to "yu", "я" to "ya"
    )

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts = fullName?.split(" ")
        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)
        if (firstName.isNullOrBlank()) firstName = null
        if (lastName.isNullOrBlank()) lastName = null
        return Pair(firstName, lastName)
    }



    fun toInitials(firstName: String?, lastName: String?): String? {
        var initials: String? = ""
        if (!firstName.isNullOrBlank()) initials += firstName[0].toUpperCase()
        if (!lastName.isNullOrBlank()) initials += lastName[0].toUpperCase()
        return if (initials!!.isBlank()) null else initials
    }

    fun transliteration(payload: String?, divider: String = " "): String? {
        var result = ""
        val defaultDivider: Boolean = divider == " "
        return if (payload != null) {
            for (i: Char in payload) {
                if (dictionary.containsKey("$i")) {
                    result += dictionary["$i"]
                } else if (dictionary.containsKey("${i.toLowerCase()}")) {
                    val char = dictionary["${i.toLowerCase()}"]
                    result += char!![0].toUpperCase() + char.substring(1)
                } else if (!defaultDivider && i == ' ') {
                    result += divider
                } else {
                    result += i
                }
            }
            result
        } else
            null
    }

    interface Pluralable {
        val words: Array<String>
        fun plural(value: Long): String {
            return if (value % 100 in 11..13)
                words[0]
            else
                when (value % 10) {
                    in 2L..4L -> words[2]
                    1L -> words[1]
                    else -> words[0]
                }
        }
    }


}