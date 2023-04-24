package com.example.myapplication

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentEmailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EmailFragment : Fragment() {

    private var _binding:FragmentEmailBinding?=null
    private val binding get() = _binding!!
    private val apiService = RetrofitClient.apiService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEmailBinding.inflate(layoutInflater,container,false)

        signIn()

        return binding.root
    }
    private fun validForm():Boolean{
        val icon = AppCompatResources.getDrawable(requireContext(),R.drawable.baseline_warning_24)

        icon?.setBounds(0,0,icon.minimumWidth,icon.minimumHeight)
        when{
            TextUtils.isEmpty(binding.email.text.toString().trim())->{
                binding.email.setError("Please Enter Email",icon)
            }
            binding.email.text.toString().isNotEmpty()->{
                if (binding.email.text.toString().matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))){
                    return true
                }else{
                    binding.email.setError("Please Valid Form", icon)
                }
            }
        }
        return false
    }

    private fun sendCode(){
        val email = binding.email.text.toString()
        apiService.sendCode(email).enqueue(object : Callback<SendCode>{
            override fun onResponse(call: Call<SendCode>, response: Response<SendCode>) {
                if(response.isSuccessful){
                    val codeFragment = CodeFragment.newInstance(email)
                        parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view,codeFragment)
                        .addToBackStack(null)
                        .commit()
                    Toast.makeText(requireContext(),response.body()?.message,Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireContext(),"error",Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<SendCode>, t: Throwable) {}
        })
    }

    private fun signIn(){
        binding.goCode.isEnabled = false
        binding.email.addTextChangedListener {
            val email = binding.email.text.toString()
            var status = false
            if(email.isNotEmpty()){
                if (validForm()){
                    status = true
                }
            }
            binding.goCode.isEnabled = status
            binding.goCode.setOnClickListener{
                findNavController().navigate(R.id.action_emailFragment_to_codeFragment)
                sendCode()
            }
        }
    }
}