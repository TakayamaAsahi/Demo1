package com.bw.longfromvideo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyPagerAdapter:FragmentPagerAdapter {
    lateinit var list:List<Fragment>
    lateinit var title:List<String>

    constructor(fm: FragmentManager, list: List<Fragment>, title: List<String>) : super(fm) {
        this.list = list
        this.title = title
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Fragment {
        return list.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return title.get(position)
    }


}