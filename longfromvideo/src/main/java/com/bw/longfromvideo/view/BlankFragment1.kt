package com.bw.longfromvideo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bw.longfromvideo.R
import com.bw.longfromvideo.databinding.FragmentBlank1Binding

class BlankFragment1 : Fragment() {
    lateinit var binding:FragmentBlank1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBlank1Binding.inflate(layoutInflater)

        var bd:Bundle = Bundle()
        var t1 = this.requireArguments().getString("title").toString()
        var t2 = this.requireArguments().getString("jj").toString()
        binding.b1Title.setText(t1)
        binding.b1Text.setText(t2)
        return binding.root
    }
}