package com.gregory.ideabox.managers

object IBManager {
    var firebaseManager: FirebaseManager
    var authenticationManager: AuthenticationManager

    init {
        firebaseManager = FirebaseManager()
        authenticationManager = AuthenticationManager(firebaseManager)
    }

}