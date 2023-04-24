package com.example.myapplication

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentPasswordBinding


class PasswordFragment : Fragment() {

    private var _binding:FragmentPasswordBinding?=null
    private val binding get () = _binding!!

    private var pinCode: String = ""
    private lateinit var del:ImageView
    private lateinit var pinCircle:List<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPasswordBinding.inflate(layoutInflater, container, false)
            pinCircle = listOf(binding.circle,binding.circleTwo,binding.circleThree,binding.circleFour)
        del = binding.pinDel
        del.setOnClickListener {
            if(pinCode.isNotEmpty()){
                pinCode = pinCode.dropLast(1)
                upDateCircle()
            }
        }
        binding.pin0.setOnClickListener { pinDigit(0) }
        binding.pin1.setOnClickListener { pinDigit(1) }
        binding.pin2.setOnClickListener { pinDigit(2) }
        binding.pin3.setOnClickListener { pinDigit(3) }
        binding.pin4.setOnClickListener { pinDigit(4) }
        binding.pin5.setOnClickListener { pinDigit(5) }
        binding.pin6.setOnClickListener { pinDigit(6) }
        binding.pin7.setOnClickListener { pinDigit(7) }
        binding.pin8.setOnClickListener { pinDigit(8) }
        binding.pin9.setOnClickListener { pinDigit(9) }

        return binding.root
    }
    private fun upDateCircle(){
        pinCircle.forEachIndexed { index, view ->
            if(index < pinCode.length){
                view.setBackgroundResource(R.drawable.circle_blue)
            }else{
                view.setBackgroundResource(R.drawable.circle_circle)
            }
        }
        if(pinCode.length == pinCircle.size){
            findNavController().navigate(R.id.action_passwordFragment_to_createUserr)
        }
    }
    private fun pinDigit(digit:Int){
        if(pinCode.length < pinCircle.size){
            pinCode += digit.toString()
            upDateCircle()
        }
    }
}