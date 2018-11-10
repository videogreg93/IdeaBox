package com.gregory.ideabox.views.login

import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser
import com.gregory.ideabox.views.base.BasePresenter
import com.gregory.ideabox.views.base.BaseView

interface LoginContract {

    interface View : BaseView<Presenter> {
        fun doOnAuthentication(account: FirebaseUser)
        fun doOnError(t: Throwable?)
    }

    interface Presenter : BasePresenter<LoginContract.View> {
        fun authenticate(requestCode: Int)
        fun getUserSignedIn()
        fun getUser(data: Intent?)
    }
}