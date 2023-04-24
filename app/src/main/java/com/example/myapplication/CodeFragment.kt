package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentCodeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CodeFragment : Fragment() {

    private var _binding: FragmentCodeBinding?=null
    private val binding get() = _binding!!

    private lateinit var timerEditText:TextView
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var editTextList: List<EditText>

    private val apiService = RetrofitClient.apiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // Inflate the layout for this fragment
        _binding = FragmentCodeBinding.inflate(layoutInflater,container,false)

        initView()
        initListener()
        showKeyBoard()

        editTextList.forEachIndexed { index, editText ->
            editText.setOnKeyListener { view, i, keyEvent ->
                if(i == KeyEvent.KEYCODE_DEL && keyEvent.action == KeyEvent.ACTION_DOWN){
                    val del = view as EditText
                    if (del.text.isEmpty() && index > 0){
                        val prevEditText = editTextList[index - 1]
                        prevEditText.requestFocus()
                        prevEditText.text.clear()
                    }
                    return@setOnKeyListener true
                }
                false
            }
        }


        return binding.root
    }
    private fun showKeyBoard(){
        val first = editTextList.firstOrNull()
        first?.requestFocus()
        val inputManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.showSoftInput(first,InputMethodManager.SHOW_IMPLICIT)
    }

    private fun initView(){
        timerEditText = binding.time
        editTextList = listOf(binding.OTP1,binding.OTP2,binding.OTP3,binding.OTP4)
    }

    private fun initListener(){
        editTextList.forEachIndexed { index, editText ->
            editText.addTextChangedListener(
                CodeTextWatcher(
                    editTextList.getOrNull(index + 1),
                    editTextList.getOrNull(index - 1),
                    editText
                )
            )
        }
    }

    inner class CodeTextWatcher(private val nextEditText: EditText?, private val prevEditTExt:EditText?,currentEditText: EditText):TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if(s?.length == 1){
                nextEditText?.let {
                    it.requestFocus()
                    it.setSelection(it.text.length)
                }?: run{
                    navigationToNextFragment()
                }
            }else if((s?.length?: 0) > 1){
                prevEditTExt?.let {
                    it.requestFocus()
                    it.extendSelection(it.text.length)
                }
            }
        }
        override fun afterTextChanged(s: Editable?) {}
    }

    private fun navigationToNextFragment(){
        val email = arguments?.getString("email")?:""
        val code = editTextList.joinToString("") {it.text.toString()}
        GlobalScope.launch {
            apiService.signIn(email,code).enqueue(object : Callback<SignIn>{
                override fun onResponse(call: Call<SignIn>, response: Response<SignIn>) {
                    if(response.isSuccessful){
                        val token = response.body()?.token?:""
                        findNavController().navigate(R.id.action_codeFragment_to_passwordFragment)
                    }else{
                        Toast.makeText(requireContext(),"error",Toast.LENGTH_SHORT).show()
                        countDownTimer = object : CountDownTimer(60000,1000){
                            override fun onTick(millis: Long) {
                                timerEditText.text = "Отправить код повторно можно\n" + "будет через${millis/1000}"
                            }

                            override fun onFinish() {
                                timerEditText.text = ""
                            }
                        }.start()
                    }
                }
                override fun onFailure(call: Call<SignIn>, t: Throwable) {}
            })
        }
    }




  companion object{
      fun newInstance(email:String):CodeFragment{
          val fragment = CodeFragment()
          val args = Bundle()
          args.putString("email",email)
          fragment.arguments = args
          return fragment
      }
  }
}