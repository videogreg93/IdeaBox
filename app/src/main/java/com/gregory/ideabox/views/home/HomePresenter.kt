package com.gregory.ideabox.views.home

import com.gregory.ideabox.managers.AuthenticationManager
import com.gregory.ideabox.managers.IBManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomePresenter(override var myView: HomeContract.View, var authenticationManager: AuthenticationManager = IBManager.authenticationManager): HomeContract.Presenter {

    init {
        myView.presenter = this
    }

    override fun getUser() {
        GlobalScope.launch(Dispatchers.Main) {
            myView.onGetUser(authenticationManager.getCurrentUser())
        }
    }
}