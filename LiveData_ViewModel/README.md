# ViewModel과 LiveData
뷰모델 + 라이브데이터 + 데이터바인딩 조합으로 자주 사용됨!

## 🙌View Model
#### 1-1. 개념
: AAC(Android Architecture Components) 중 하나로, <br>
수명 주기를 고려하여 UI 관련 데이터를 저장하고 관리해주는 라이브러리 <br><br>
_**-AAC**: 클린 아키텍처를 쉽게 구현할 수 있도록 만든 라이브러리 모음집_
<br><br>

#### 1-2. 사용하는 이유
: 뷰모델은 NonConfigurationInstances 객체로서 UI 컨트롤러의 생명주기와 관계없이 별도로 관리됨. <br>
따라서 아래의 장점이 있음.

- UI 컨트롤러(액티비티, 프래그먼트)와 데이터의 분리 
  - UI 컨트롤러의 본목적은 운영체제 커뮤니케이션 처리(사용자 작업에 반응, 권한 요청 등)
  - 메모리 부족 등의 이유로 운영체제가 UI클래스를 제거할 수 있음
  - 데이터베이스 혹은 네트워크 관련 처리를 하지 않는 것이 좋음 ⇒로드 시간이 지연될 수 있음
- 생애주기에 관계없이 데이터 유지 가능
  - EX) 화면 회전의 경우, 세로화면 onDestroy() → 가로화면 onCreate()가 되면 데이터가 날라가게 됨. `saveInstanceState`는 50Kb의 데이터만 담을 것을 권장하기 때문에 제한적.
- 프래그먼트 간의 데이터 공유 용이

![image](https://user-images.githubusercontent.com/44793355/163011151-8a5389aa-090d-4792-9e92-b87822ae1791.png)
<br><br>

#### 1-3. 사용법
A. 모듈 수준 gradle에 dependencies 추가
```
// 뷰모델 
implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.0-alpha04")  
// 라이브데이터
implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.0-alpha04")  
```

B. 뷰모델 파일 만들기
```Kotlin
class MyNumberViewModel : ViewModel(){
    private val _currentValue = MutableLiveData<Int>()
    val currentValue : LiveData<Int>
        get() = _currentValue

    init{
        _currentValue.value = 0
    }
    
     fun updateValue(actionType: NumberActionType, input:Int){
        when(actionType){
            NumberActionType.PLUS ->
                _currentValue.value = _currentValue.value?.plus(input)
            NumberActionType.MINUS ->
                _currentValue.value = _currentValue.value?.minus(input)
        }
    }
}
```

C. 액티비티 파일
```Kotlin
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var binding: ActivityMainBinding? = null
    lateinit var myNumberViewModel: MyNumberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        myNumberViewModel = ViewModelProvider(this).get(MyNumberViewModel::class.java)  // 💡

        myNumberViewModel.currentValue.observe(this, Observer{
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
```
💡뷰모델 인스턴스는 `ViewModelProvider`을 통해 만든다. 이때 `this`는 MainActivity를 가리킨다. <br>
이 MainActivity는 HashMap 구조의 `ViewModelStore`를 가지고 있어서 MyNumberViewModel을 키로 값을 찾는다. <br>
즉, 하나의 액티비티를 소유자로 지정하면 같은 뷰모델을 공유할 수 있다. = 데이터 공유 가능 <br>
뷰모델을 각각 다른 소유자가 생성하면 별개의 메모리 공간을 사용하는 다른 객체가 된다.

<br>

#### 1-4. 주의할 점
- 뷰모델에 context 저장하지 않기. 이미 destroy된 액티비티에 묶여 Memory leak이 발생할 수 있기 때문.
- Application context를 사용한다면 `AndroidViewModel` 클래스를 상속받으면 됨.
- 뷰모델(많은 데이터 보관)과 onSaveInstanceState(적고 UI 상태를 되돌릴 만한 데이터 보관)는 함께 이용 가능.
    - EX) `ViewModel`: 유저 아이디, 이름, 생일, 이미지.. / `onSaveInstanceState`: 유저 아이디

<br><br>

## 🙌Live Data
#### 2-1. 개념
: 관찰이 가능한 데이터 홀더 클래스. <br>
: 안드로이드의 Observer 클래스는 라이브 데이터 내의 데이터를 관찰하다가 변경 시 UI 컨트롤러에게 알림을 보내고, <br>
UI 컨트롤러는 변경된 데이터를 UI에 반영
<br><br>

#### 2-2. 사용하는 이유
[Android Developers - LiveData 사용의 이점](https://developer.android.com/topic/libraries/architecture/livedata?hl=ko#the_advantages_of_using_livedata)
<br><br>

#### 2-3.사용법
_✓ 관용적으로 `MutableLiveData` 변수명 앞에는 `_`를 붙이고 `LiveData`는 붙이지 않는다._ <br>

A. 액티비티 파일
(위의 코드와 중복되어 추가 설명할 부분만 기술)
```Kotlin
 myNumberViewModel.currentValue.observe(this, Observer{
            binding!!.numberTextview.text = it.toString()
        })

```
여기에서 this는 `Observer`를 생성한 액티비티 말함. `observe()` 메소드는 `LifecycleOwner` 객체를 사용하기 때문에 `Observer`의 생명주기는 UI 컨트롤러의 생명주기 즉, this의 생명주기를 따름. <br>
*Fragment의 경우에는 `this`가 아니라 `viewLifecycleOwner`을 사용. <br>
*`observeForever` 메서드를 사용하면 LifecycleOwner 객체가 없는 관찰자를 등록할 수 있음. 그러면 관계없이 항상 알림을 받을 수 있음. 대신 `removeObserver` 메소드를 사용해 직접 삭제해줘야 함.



<br><br>
### 🧱 참고 사이트
[취준생을 위한 안드로이드 앱만들기 라이브데이터 뷰모델 YOUTUBE](https://www.youtube.com/watch?v=-b0VNKw_niY&list=WL&index=10&t=2s) <br>
[Android Developers](https://developer.android.com/topic/libraries/architecture/viewmodel?hl=ko) <br>
[오늘의 코드 블로그](https://todaycode.tistory.com/33)
