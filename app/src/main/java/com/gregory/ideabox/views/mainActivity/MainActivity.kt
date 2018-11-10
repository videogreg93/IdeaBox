package com.gregory.ideabox.views.mainActivity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
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
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_ideas -> {
                ViewUtils.displayFragmentWithoutArgs(this, CategoriesFragment(), false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        // set default fragment
        ViewUtils.displayFragmentWithoutArgs(this, HomeFragment(), false)
    }
}
