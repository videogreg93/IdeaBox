package com.gregory.ideabox.views.ideas

import com.gregory.ideabox.models.Idea
import com.gregory.ideabox.views.base.BasePresenter
import com.gregory.ideabox.views.base.BaseView

interface IdeasContract {

    interface View : BaseView<Presenter> {
        fun onIdeaAdded()
        fun onError()
    }

    interface Presenter : BasePresenter<View> {
        fun addIdea(idea: Idea)
    }
}