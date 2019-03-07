package com.gregory.ideabox.views.categories

import com.gregory.ideabox.models.Category
import com.gregory.ideabox.models.Idea
import com.gregory.ideabox.views.base.BasePresenter
import com.gregory.ideabox.views.base.BaseView

interface CategoriesContract {

    interface View : BaseView<Presenter> {
        fun onGetCategories(categories: ArrayList<Category>)
        fun onGetIdeas(ideas: List<Idea>)
    }

    interface Presenter : BasePresenter<View> {
        fun getCategories()
        fun getIdeasForCategories(category: String)
    }

}