package com.gregory.ideabox.views.categories


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import com.gregory.ideabox.R
import com.gregory.ideabox.models.Category
import com.gregory.ideabox.models.Idea
import com.gregory.ideabox.views.base.ViewUtils
import com.gregory.ideabox.views.ideas.IdeasFragment
import kotlinx.android.synthetic.main.fragment_ideas.*
import kotlinx.android.synthetic.main.fragment_ideas.view.*

class CategoriesFragment : Fragment(), CategoriesContract.View {
    override lateinit var presenter: CategoriesContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ideas, container, false)
        CategoriesPresenter(this)
        view.add_idea_button?.visibility = View.GONE
        presenter.getCategories()
        return view
    }

    @SuppressLint("PrivateResource")
    override fun onGetCategories(categories: ArrayList<Category>) {
        list?.adapter = ArrayAdapter<Category>(requireContext(), R.layout.support_simple_spinner_dropdown_item, categories)
        list?.setOnItemClickListener { parent, view, position, id ->
            val category = categories[position]
            presenter.getIdeasForCategories(category.name)
        }
    }

    override fun onGetIdeas(ideas: List<Idea>) {
        val bundle = Bundle()
        bundle.putParcelableArray(IDEA_ARG, ideas.toTypedArray())
        ViewUtils.displayFragmentWithArgs(activity!!, IdeasFragment(), false, bundle)
    }

    companion object {
        const val IDEA_ARG = "IDEAS"
    }
}
