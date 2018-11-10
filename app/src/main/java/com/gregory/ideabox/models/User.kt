package com.gregory.ideabox.models

import com.google.firebase.auth.FirebaseUser

class User(var displayName: String = "", var email: String = "", var friends: ArrayList<User> = ArrayList()) {

    companion object {
        fun createUser(firebaseUser: FirebaseUser): User  {
            var displayName = firebaseUser.displayName.orEmpty()
            var email = firebaseUser.email.orEmpty()
            return User(displayName,email)
        }
    }

}