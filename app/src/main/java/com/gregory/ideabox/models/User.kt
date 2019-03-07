package com.gregory.ideabox.models

import com.google.firebase.auth.FirebaseUser

class User(
    var displayName: String = "",
    var email: String = "",
    var friends: ArrayList<User> = ArrayList(),
    var id: String = "",
    var Categories: ArrayList<Category> = ArrayList()
) {

    companion object {
        fun createUser(firebaseUser: FirebaseUser, defaultCategories: ArrayList<Category>): User {
            val displayName = firebaseUser.displayName.orEmpty()
            val email = firebaseUser.email.orEmpty()
            val id = firebaseUser.uid
            // TODO get default categories
            return User(displayName, email, ArrayList(), id, defaultCategories)
        }
    }

}