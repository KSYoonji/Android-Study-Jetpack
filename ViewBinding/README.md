## View Binding
_✓ 해당 개념은 지금까지 써왔던 관계로 정리만 하고 넘어간다!_
<br><br>

**사용법**
- gradle 추가
```
android {
    ...
    buildFeatures {
        viewBinding = true
    }
}
```

- ActivityMain 파일
```Kotlin
private lateinit var binding: ActivityMainBinding

override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.plusBtn.setOnClickListener(this)
        binding.minusBtn.setOnClickListener(this)
}
```

- HomeFragment 파일
```Kotlin
private var _binding: FragmentHomeBinding? = null
private val binding get() = _binding!!

override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {    // 💡
        super.onDestroyView()
        _binding = null
    }
```
💡프래그먼트 Life cycle 상 프래그먼트는 뷰보다 오래 살아있을 수 있다. 이 경우 Memory leak이 일어나기 때문에 뷰가 삭제될 때 프래그먼트도 null을 이용해 삭제해주는 것이다.
하지만 매번 위의 코드를 쓰는 것은 번거로운 일이다. <br>
따라서 해당 이슈를 해결하기 위해서 BaseActivity, BaseFragment 클래스 만들기 / property delegation 이용하기 / 
onCreateView() 또는 onViewCreated()에서 View Binding 참조 끝내기 등의 방법이 사용되고 있다,

<br><br>

### 🧱참고 사이트
[Android Developers](https://developer.android.com/topic/libraries/view-binding)
