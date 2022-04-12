# ViewModel과 LiveData

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

<br>

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
_✓ 관용적으로 MutableLiveData 변수명 앞에는 `_`를 붙이고 LiveData는 붙이지 않는다._


<br><br>
### 🧱 참고 사이트
[취준생을 위한 안드로이드 앱만들기 라이브데이터 뷰모델 YOUTUBE](https://www.youtube.com/watch?v=-b0VNKw_niY&list=WL&index=10&t=2s) <br>
[Android Developers](https://developer.android.com/topic/libraries/architecture/viewmodel?hl=ko) <br>
[오늘의 코드 블로그](https://todaycode.tistory.com/33)
