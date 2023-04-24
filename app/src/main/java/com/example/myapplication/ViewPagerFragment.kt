package com.example.myapplication

import android.media.Image
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.databinding.FragmentViewPagerBinding


class ViewPagerFragment : Fragment() {

    private var _binding:FragmentViewPagerBinding?=null
    private val binding get () = _binding!!
    private lateinit var dots: Array<ImageView>
    private lateinit var dotsLayout:LinearLayout
    private lateinit var viewPager:ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentViewPagerBinding.inflate(layoutInflater,container,false)

       viewPager = binding.viewPager
        dotsLayout = binding.dotsLayout

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                setCurrentDots(position)
            }
        })
        setUpDots()
        setCurrentDots(0)

        return binding.root
    }
    private fun setUpDots(){
        val onBoarding = listOf(FirstScreen(),SecondFragment(),ThirdFragment())
        val adapter = ViewPagerAdapter(requireActivity(),onBoarding)
        viewPager.adapter = adapter

        dots = Array(onBoarding.size){ ImageView(requireContext()) }
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(9,0,9,0)
        for(i in dots.indices){
            dots[i].setImageDrawable(ContextCompat.getDrawable(requireContext().applicationContext,R.drawable.elips_zero))
            dotsLayout.addView(dots[i],params)
        }
    }
    private fun setCurrentDots(position:Int){
        for (i in dots.indices){
            dots[i].setImageDrawable(ContextCompat.getDrawable(requireContext().applicationContext,R.drawable.elips_zero))
        }
        dots[position].setImageDrawable(ContextCompat.getDrawable(requireContext().applicationContext,R.drawable.ellips))
    }
    //sdfsdfsdf
}