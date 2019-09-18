package com.gregory.ideabox.views.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import com.gregory.ideabox.models.User
import kotlinx.android.synthetic.main.fragment_home.*
import android.R

class HomeFragment : Fragment(), HomeContract.View {
    override lateinit var presenter: HomeContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.gregory.ideabox.R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        HomePresenter(this)
        presenter.getUser()
    }

    override fun onGetUser(user: User) {
        title?.text = getString(com.gregory.ideabox.R.string.home_title, user.displayName)
        spinner?.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, user.Categories)
    }
}
