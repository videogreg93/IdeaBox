package com.gregory.ideabox.views.ideas


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import com.gregory.ideabox.R
import com.gregory.ideabox.models.Category
import com.gregory.ideabox.models.Idea
import com.gregory.ideabox.views.categories.CategoriesFragment
import kotlinx.android.synthetic.main.fragment_ideas.*
import kotlinx.android.synthetic.main.fragment_ideas.view.*

class IdeasFragment : Fragment(), IdeasContract.View {
    override lateinit var presenter: IdeasContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ideas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        IdeasPresenter(this)
        add_idea_button?.visibility = View.VISIBLE
        add_idea_button?.setOnClickListener {
            // TODO add idea
            presenter.addIdea(Idea("Test Idea", "Movies"))
        }
        // TODO link up to viewmodel to get ideas
        getIdeas()
    }

    private fun getIdeas() {
        val ideas: Array<Idea> = arguments?.get(CategoriesFragment.IDEA_ARG) as Array<Idea>
        list?.adapter = ArrayAdapter<Idea>(requireContext(), R.layout.support_simple_spinner_dropdown_item, ideas)
    }

    override fun onIdeaAdded() {
        // TODO not do this because we'll be linked to viewmodel
        getIdeas()
    }

    override fun onError() {

        Log.e("IdeasFragment", "Could not get ideas")
    }


}
