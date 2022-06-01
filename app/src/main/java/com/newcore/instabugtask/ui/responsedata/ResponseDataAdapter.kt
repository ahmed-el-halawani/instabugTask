package com.newcore.instabugtask.ui.responsedata

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerFragment(val title: String, val fragment: Fragment)

class ResponseDataAdapter(fragment: Fragment, val fragments: List<PagerFragment>) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position].fragment
    }
}