package com.example.assessmentdemo.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.assessmentdemo.ui.home.favorite.FavoritesFragment
import com.example.assessmentdemo.ui.home.post.PostsFragment

class HomePagerAdapter(
    activity: FragmentActivity
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        if (position == 0) PostsFragment() else FavoritesFragment()
}
