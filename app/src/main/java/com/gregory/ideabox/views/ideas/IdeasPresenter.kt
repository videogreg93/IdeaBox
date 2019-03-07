package com.gregory.ideabox.views.ideas

import com.gregory.ideabox.managers.AuthenticationManager
import com.gregory.ideabox.managers.FirebaseManager
import com.gregory.ideabox.managers.IBManager
import com.gregory.ideabox.models.Idea
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.android.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IdeasPresenter(
    override var myView: IdeasContract.View,
    val firebaseManager: FirebaseManager = IBManager.firebaseManager,
    val authenticationManager: AuthenticationManager = IBManager.authenticationManager
) : IdeasContract.Presenter {
    init {
        myView.presenter = this
    }

    override fun addIdea(idea: Idea) {
        GlobalScope.launch {
            try {
                firebaseManager.addIdea(authenticationManager.myId, idea)
                withContext(Dispatchers.Main) {
                    myView.onIdeaAdded()
                }
            } catch (e: Exception) {
                myView.onError()
            }
        }
    }
}