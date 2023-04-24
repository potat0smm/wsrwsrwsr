package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentFirstScreenBinding
import com.example.myapplication.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {

    private var _binding:FragmentThirdBinding?=null
    private val biniding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentThirdBinding.inflate(layoutInflater,container,false)

        biniding.swipeThird.setOnClickListener{
            findNavController().navigate(R.id.action_viewPagerFragment_to_emailFragment)
        }

        return biniding.root
    }


}