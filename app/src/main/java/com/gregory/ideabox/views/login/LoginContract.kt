package com.gregory.ideabox.views.login

import android.content.Intent
import com.gregory.ideabox.views.base.BasePresenter
import com.gregory.ideabox.views.base.BaseView

interface LoginContract {

    interface View : BaseView<Presenter> {
        fun doOnAuthentication()
        fun doOnError(t: Throwable?)
    }

    interface Presenter : BasePresenter<LoginContract.View> {
        fun authenticate(requestCode: Int)
        fun getUserSignedIn()
        fun getUser(data: Intent?)
    }
}