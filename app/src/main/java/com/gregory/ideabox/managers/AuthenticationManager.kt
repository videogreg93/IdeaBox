package com.gregory.ideabox.managers

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.gregory.ideabox.models.User

class AuthenticationManager(val firebaseManager: FirebaseManager) {
    var myId: String = ""

    suspend fun getCurrentUser(): User {
        return firebaseManager.getUser(myId)
    }
}