package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentCreateUserrBinding
import kotlinx.coroutines.GlobalScope


class CreateUser : Fragment() {/*

    private var _binding: FragmentCreateUserrBinding? = null
    private val binding get() = _binding!!
    private val apiService = RetrofitClient.apiService
    private lateinit var signInViewModel:

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateUserrBinding.inflate(inflater, container, false)
        signInViewModel = ViewModelProvider(requireActivity()).get(SignInViewModel::class.java)

        binding.goMenu.setOnClickListener {
            createUser()
            findNavController().navigate(R.id.action_createUserr_to_menuFragment)
        }

        return binding.root
    }

    private fun createUser() {
        val token = signInViewModel.getToken()
        val firstname = binding.editName.text.toString()
        val middlename = binding.editPatronymic.text.toString()
        val lastname = binding.editSurname.text.toString()
        val bith = binding.editToBirthday.text.toString()
        val pol = binding.autoComplete.text.toString()

        val createUserRequest = CreateUserRequest(
            firstname = firstname,
            middlename = middlename,
            lastname = lastname,
            bith = bith,
            pol = pol
        )

        GlobalScope.launch {
            apiService.createUser("Bearer $token", createUserRequest).enqueue(object : Callback<CreateUserResponse> {
                override fun onResponse(call: Call<CreateUserResponse>, response: Response<CreateUserResponse>) {
                    if (response.isSuccessful) {
                        val id = response.body()?.id ?: ""
                        val bith = response.body()?.bith ?: ""
                        val firstname = response.body()?.firstname ?: ""
                        val lastname = response.body()?.lastname ?: ""
                        val middlename = response.body()?.middlename ?: ""
                        val pol = response.body()?.pol ?: ""
                        signInViewModel.saveUser(id as Int, bith, firstname, lastname, pol, middlename)
                        Toast.makeText(requireContext(), "Пользователь успешно создан", Toast.LENGTH_SHORT).show()
                    } else {
                        Log.e("CreateUser", "Ошибка при создании пользователя: ${response.code()} - ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<CreateUserResponse>, t: Throwable) {
                    Log.e("CreateUser", "Ошибка при создании пользователя", t)
                }
            })
        }
    }*/
}