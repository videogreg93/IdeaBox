package com.gregory.ideabox.views.categories


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import com.gregory.ideabox.R
import com.gregory.ideabox.models.Category
import kotlinx.android.synthetic.main.fragment_ideas.*

class CategoriesFragment : Fragment(), CategoriesContract.View {
    override lateinit var presenter: CategoriesContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ideas, container, false)
        CategoriesPresenter(this)
        presenter.getCategories()
        return view
    }

    @SuppressLint("PrivateResource")
    override fun onGetCategories(categories: ArrayList<Category>) {
        list.adapter = ArrayAdapter<Category>(context!!, R.layout.support_simple_spinner_dropdown_item, categories)
        list.setOnItemClickListener { parent, view, position, id ->
            val category = categories[position]
            Log.d("CategoriesFragment", category.toString())
        }
    }
}
