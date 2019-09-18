package com.gregory.ideabox.views.categories

import com.gregory.ideabox.managers.AuthenticationManager
import com.gregory.ideabox.managers.FirebaseManager
import com.gregory.ideabox.managers.IBManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoriesPresenter(
    override var myView: CategoriesContract.View,
    val authenticationManager: AuthenticationManager = IBManager.authenticationManager,
    val firebaseManager: FirebaseManager = IBManager.firebaseManager
) : CategoriesContract.Presenter {

    init {
        myView.presenter = this
    }

    override fun getCategories() {
        //todo implement
        // Get default categories, then custom categories
        GlobalScope.launch() {
            val categories = authenticationManager.getCurrentUser().Categories
            withContext(Dispatchers.Main) {
                myView.onGetCategories(categories)
            }
        }
    }

    override fun getIdeasForCategories(category: String) {
        GlobalScope.launch {
            val ideas = firebaseManager.getIdeasForCategory(authenticationManager.myId,category).map {
                it.second
            }
            withContext(Dispatchers.Main) {
                myView.onGetIdeas(ideas)
            }
        }
    }
}