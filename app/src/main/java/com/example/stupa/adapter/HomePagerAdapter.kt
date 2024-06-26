package com.example.stupa.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.stupa.view.fragment.UserFragment
import com.example.stupa.view.fragment.UserListFragment

// number of tabs to show in home activity
private const val NUM_TABS = 2

// adapter for home pager
class HomePagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return UserFragment()
            1 -> return UserListFragment()
        }
        return UserFragment()
    }
}