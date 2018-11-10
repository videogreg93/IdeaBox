package com.gregory.ideabox.views.categories

import com.gregory.ideabox.managers.FirebaseManager
import com.gregory.ideabox.managers.IBManager
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import kotlin.coroutines.experimental.CoroutineContext

class CategoriesPresenter(override var myView: CategoriesContract.View,
                          val firebaseManager: FirebaseManager = IBManager.firebaseManager) : CategoriesContract.Presenter {
    init {
        myView.presenter = this
    }

    override fun getCategories() {
        //todo implement
        // Get default categories, then custom categories
        launch {
            val categories = firebaseManager.getCategories()
            withContext(UI) {
                myView.onGetCategories(categories)
            }

        }
    }
}