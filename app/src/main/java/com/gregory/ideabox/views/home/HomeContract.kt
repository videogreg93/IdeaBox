package com.gregory.ideabox.views.home

import com.gregory.ideabox.models.User
import com.gregory.ideabox.views.base.BasePresenter
import com.gregory.ideabox.views.base.BaseView

interface HomeContract {

    interface View: BaseView<Presenter>{
        fun onGetUser(user: User)
    }

    interface Presenter: BasePresenter<View> {
        fun getUser()
    }
}