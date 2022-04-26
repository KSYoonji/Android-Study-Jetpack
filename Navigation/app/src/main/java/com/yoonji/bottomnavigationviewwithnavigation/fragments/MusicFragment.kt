package com.yoonji.bottomnavigationviewwithnavigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yoonji.bottomnavigationviewwithnavigation.databinding.FragmentHomeBinding
import com.yoonji.bottomnavigationviewwithnavigation.databinding.FragmentMusicBinding

class MusicFragment: Fragment() {

    private var binding: FragmentMusicBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMusicBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding=null
    }
}