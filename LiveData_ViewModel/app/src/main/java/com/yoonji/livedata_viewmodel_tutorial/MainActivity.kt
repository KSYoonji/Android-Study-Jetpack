package com.yoonji.livedata_viewmodel_tutorial

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yoonji.livedata_viewmodel_tutorial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var binding: ActivityMainBinding? = null
    lateinit var myNumberViewModel: MyNumberViewModel
    companion object {
        const val TAG : String = "로그"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        // ViewModelProvider를 통해 ViewModel 가져오고,
        // 라이프사이클을 가지고 있는 녀석(자기자신)을 넣어줌
        // 우리가 가져오고 싶은 뷰모델 클래스를 넣어 뷰모델 가져오기
        myNumberViewModel = ViewModelProvider(this).get(MyNumberViewModel::class.java)

        // 뷰모델이 가지고 있는 값의 변경사항을 관찰할 수 있는 Live Data Observing
        myNumberViewModel.currentValue.observe(this, Observer{
            Log.d(TAG, "MainActivity - myNumberViewModel - currentValue 라이브 데이터 값 변경 : $it")
            binding!!.numberTextview.text = it.toString()
        })

        binding!!.plusBtn.setOnClickListener(this)
        binding!!.minusBtn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val userInput = binding?.userinputEdittext?.text.toString().toInt()

        when(view){
            binding?.plusBtn ->
                myNumberViewModel.updateValue(actionType = NumberActionType.PLUS, userInput)
            binding?.minusBtn ->
                myNumberViewModel.updateValue(actionType = NumberActionType.MINUS, userInput)
        }

    }
}