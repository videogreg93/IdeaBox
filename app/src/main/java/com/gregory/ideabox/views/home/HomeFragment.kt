package com.gregory.ideabox.views.home


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.gregory.ideabox.R
import com.gregory.ideabox.models.User


class HomeFragment : Fragment(), HomeContract.View {

    override lateinit var presenter: HomeContract.Presenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        HomePresenter(this)
    }

    override fun onGetUser(user: User) {

    }
}
