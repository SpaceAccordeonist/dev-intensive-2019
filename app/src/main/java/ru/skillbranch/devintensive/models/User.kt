package ru.skillbranch.devintensive.models

import java.util.*

class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    var lastVisit: Date? = Date(),
    var isOnline: Boolean = false
) {

    constructor(id: String, firstName: String?, lastName: String?)
            : this(id = id, firstName = firstName, lastName = lastName, avatar = null)


    companion object Factory {
        var lastId: Int = -1;
        fun makeUser(fullName: String?): User {
            var firstName: String?
            var lastName: String?
            val parts = fullName?.split(" ")
            firstName = parts?.getOrNull(0)
            lastName = parts?.getOrNull(1)
            return User("${++lastId}", firstName, lastName)
        }
    }

    class Builder {
        private var buildingUser: User = Factory.makeUser("")
        fun build(): User = buildingUser
        fun id(id: String): Builder {
            buildingUser = User(
                id, buildingUser.firstName, buildingUser.lastName,
                buildingUser.avatar, buildingUser.rating, buildingUser.respect,
                buildingUser.lastVisit, buildingUser.isOnline
            );
            return this
        }

        fun firstName(firstName: String): Builder {
            buildingUser.firstName = firstName; return this
        }

        fun lastName(lastName: String): Builder {
            buildingUser.lastName = lastName; return this
        }

        fun avatar(avatar: String): Builder {
            buildingUser.avatar = avatar; return this
        }

        fun rating(rating: Int): Builder {
            buildingUser.rating = rating; return this
        }

        fun respect(respect: Int): Builder {
            buildingUser.respect = respect; return this
        }

        fun lastVisit(lastVisit: Date): Builder {
            buildingUser.lastVisit = lastVisit; return this
        }

        fun isOnline(isOnline: Boolean): Builder {
            buildingUser.isOnline = isOnline; return this
        }
    }
}