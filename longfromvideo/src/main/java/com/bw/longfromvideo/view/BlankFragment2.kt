package com.bw.longfromvideo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bw.longfromvideo.R
import com.bw.longfromvideo.databinding.FragmentBlank2Binding

class BlankFragment2 : Fragment() {
    lateinit var binding:FragmentBlank2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlank2Binding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        return binding.root
    }
}