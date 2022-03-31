package com.yoonji.livedata_viewmodel_tutorial

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class NumberActionType{
    PLUS, MINUS
}

// 데이터의 변경
// 뷰모델은 데이터의 변경사항을 알려주는 라이브 데이터를 가지고 있음

// Mutable Live Data: 수정 가능
// Live Data: 수정 불가능. 읽기 전용

class MyNumberViewModel : ViewModel(){
    var value = 0

    // 내부에서 설정하는 자료형은 Mutable로 변경가능하도록 설정
    private val _currentValue = MutableLiveData<Int>()
    companion object {
        const val TAG : String = "로그"
    }

    // 변경되지 않는 데이터를 가져올 때 이름을 _(언더스코어) 없이 설정
    // 공개적으로 가져오는 변수는 private이 아닌 public으로 외부에서도 접근가능하도록 설정
    // 하지만 값을 직접 라이브데이터에 접근하지 않고 뷰모델을 통해 가져올 수 있도록 설정
    val currentValue : LiveData<Int>
        get() = _currentValue

    // 초기값 설정
    init{
        Log.d(TAG, "MyNumberViewModel - 생성자 호출")
        _currentValue.value = 0
    }

    // 뷰모델이 갖고 있는 값을 변경하는 메소드
    fun updateValue(actionType: NumberActionType, input:Int){
        when(actionType){
            NumberActionType.PLUS ->
                _currentValue.value = _currentValue.value?.plus(input)
            NumberActionType.MINUS ->
                _currentValue.value = _currentValue.value?.minus(input)
        }
    }
}