package com.gregory.ideabox.managers

object IBManager {
    var firebaseManager: FirebaseManager
    var authenticationManager: AuthenticationManager

    init {
        authenticationManager = AuthenticationManager()
        firebaseManager = FirebaseManager(authenticationManager)
    }

}