package com.gregory.ideabox.views.mainActivity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.gregory.ideabox.R
import com.gregory.ideabox.views.base.ViewUtils
import com.gregory.ideabox.views.categories.CategoriesFragment
import com.gregory.ideabox.views.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                navigateTo(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_ideas -> {
                navigateTo(CategoriesFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun navigateTo(fragment: Fragment, addToBackStack: Boolean = false) {
        ViewUtils.displayFragmentWithoutArgs(this, fragment, addToBackStack)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        // set default fragment
        navigateTo(HomeFragment())
    }
}
