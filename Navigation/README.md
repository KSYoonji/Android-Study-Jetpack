# Navigation
✓ 해당 컴포넌트는 **Android Studio 3.3 이상**이 필요하며, 자바 8 언어 기능을 활용합니다.

### 1. 개념
: 안드로이드 공식 문서에 따라 해석하면 탐색. 사용자가 앱 내의 여러 콘텐츠를 왔다갔다 탐색하며 일어나는 상호작용하는 것이다. <br>
Activity와 Fragment 외에 Custom View를 destination으로 지정할 수 있다.
<br><br>

### 2. 구성요소
#### 🔹Navigation Graph 
새로운 resource type으로, Navigation 관련 정보를 한 곳에 모아놓는 XML 파일. 안드로이드 스튜디오 3.3의 Navigation Editor에서 볼 수 있다. <br><br>
(1) _destination_: Navigation Editor에 나타나는 화면들로, 사용자가 이동(탐색)할 수 있는 화면들이다. 속성으로 argument, deeplink 등을 갖고있다.  <br>
(2) _action_: 사용자가 앱을 탐색할 때 destination들 간의 논리적 경로를 보여준다. <br>
<img src="https://user-images.githubusercontent.com/44793355/165335575-b4da289f-8112-487e-947c-37e8416f05cb.png"  width="600" />

#### 🔹NavHost
개별 컨텐츠인 destination들이 swap되는 빈 컨테이너이다. NavHostFragment에 의해 구현된다. 
 
#### 🔹NavController


### 2. 사용하는 이유
: 다양한 기능을 보다 쉽게 만들어 준다는 장점이 있다. 

- Bottom Navigation 구현을 쉽게 해주기
- Backstack을 간편하게 처리하기
- 프래그먼트 트랜잭션 자동화
- 딥 링크 구현 간소화
- 애니메이션 전환 처리
- Navigation Graph에서 모든 탐색에 대한 정보를 시각화하여 제공
<br><br>

### 3. 사용법
A. 모듈 수준 gradle에 dependencies 추가
```
 
```

B. 
```Kotlin

```

C. 
```Kotlin

```
💡

<br>

### 4. 주의할 점
- 



<br><br>
### 🧱 참고 사이트
[취준생을 위한 안드로이드 앱만들기 Navigation YOUTUBE](https://www.youtube.com/watch?v=4CrNKxoN_Dg) <br>
[Android Developers](https://developer.android.com/guide/navigation/navigation-getting-started) <br>
[김초희 블로그](https://choheeis.github.io/newblog//articles/2020-08/navigation)
