# ContactApp
##
### Jae Hoon
#### [FEAT] : ⚙️리사이클 뷰 xml레이아웃[activity_recycleview_item_list] 추가
->기본적인 리사이클 뷰 틀 레이아웃 구성하였습니다.
#### [FEAT] : ⚙️ 리사이클 뷰 어댑터 추가 및 어댑터 연결
->리사이클 뷰 어댑터 틀{adapter}을 추가하였습니다.
 알림 기능 추가
#### [FEAT] :⚙️[notifiationHelper],[AlterReceiver],AddContactFragMent에 알림기능 예약 밑 적용
\notification과 알람기능을 동시에 작성하였습니다.
잔버그가 있었습니다.
#### [BUG_FIX] : 🐛 [notifiationHelper],[AlterReceiver],AddContactFragMent 내용 변경
알림기능 최종수정/Item 데이터전달 
->알람매니저를 이용하여 알람기능 오류수정 및 최종완성하였습니다.
#### [FEAT] :⚙️[Item],[AddContactFragMent]수정
AddContactFragMent ->data리스트 데이터전달 함수 구현및 정렬하였습니다.
#### [FEAT] :⚙️[MainActivty]수정 연락처 데이터 전달기능 구현 및 버그 수정
->연락처 데이터들을 앱에 연동하였습니다.
->민지님과 연락처에서 받은 정보들을 datalist에 추가하는식으로 데이터를 가져와 버그 수정.

##
### Jihyeon
  
#### [FEAT] : ⚙️ addContactDial.xml 추가
- 팀회의로 전체적인 와이어 프레임을 완성하고 레이아웃 완성.

#### [FEAT] : ⚙️ add.xml 수정
- 너비와, 레이아웃 간격, 버튼 간격 등을 수정하였음.

#### [FEAT] : ⚙️ 유효성, 버튼 토스트 추가
- DialogFragment의 아웃프레임(데이터 바인딩, 유효성, 버튼 토스트) 등을 추가함.

#### [FEAT] : textwatcher
- textwatcher로 유효성과 글자수의 제한을 두는 이벤트 구현함.

#### [FEAT] : 플로팅 버튼 연결
- ContactList와 DialogFragment를 연결함.

#### [FEAT] : DialogFragment 갤러리 연결하기
- DialogFragment에서 갤러리를 연결해 사진을 바꿀 수 있도록 구현함.

#### [FEAT] : MyPage에서 갤러리 연결로 변경
- DialogFragment에서 사진 데이터를 ContectList로 원할히 넘겨주지 못하여 MyPage에서 갤러리로 연결하는 것으로 기능을 수정함.

#### [DESIGN] : :art: 앱 아이콘/런처 변경
- manifest 파일에서 앱 아이콘과 런처 이미지를 변경함.

##
### Minji

#### [DESIGN] : 🎨
- DetailDialogFragment 레이아웃 구현
- MyPageFragment 레이아웃 구현
  
#### [FEAT] : ⚙️
**[Contact List Fragment]**
- recyclerview & adapter 연결
- 아이템 swipe 시 전화 걸기 화면으로 이동
- dummy data 오름차순 정렬하여 반환

**[Detail Fragment]**
- Contact List Fragment에서 상세 data 받아 정보 표시(이름, 연락처, 담당 과, 알림, 상태 등)
- Contact List Fragment에서 아이템 클릭 → 상세 화면 다이얼로그 팝업으로 구현
- Detail Fragment 내 '전화 걸기' 버튼 클릭 → 전화 허용 권한 다이얼로그 → 사용자 허용 클릭 시 전화 걸기 화면으로 이동

**[Add Contact Fragment]**
- 연락처 추가 시 Contact List Fragment로 data 전달 및 view 즉각 반영

**[MyPage Fragment]**
- 내 정보 표시(이름, 별명, 연락처, 담당 과, bio, 상태 등)

**[NewListRepository]**
- dummy data 생성

##
### jaewon

#### [FEAT] : ⚙️ 09/04일 conflict 해결 : tablayout하고 viewpager2에 DetailContactFragment, ContactListFragment, MyPageFragment 연결

#### [FEAT] : ⚙️ 09/04일 implementation_viewpager2 추가

#### [FEAT] : ⚙️ 09/05일 mainActivity viewPager로 각 페이지 연결 성공: tablayout 아이콘,텍스트 색 변경, 페이지 스와이프

#### [FEAT] : ⚙️ 09/05일 contactlist_add_btn 추가 addcontact 버튼 추가

#### [FEAT] : ⚙️ 09/05일 mainActivity 실제 폰에 있는 연락처 불러오기(적용 실패)

#### [FEAT] : ⚙️ 09/07일 gird view : spinner 버튼의 type에 따라 뷰가 바뀌지 않음 , 바뀌는 뷰에 따라 아이템이 바뀌지 않음, 바뀌는 뷰에 맞는 holder가 생성되지 않음. 계속해서 List item과 grid item이 겹쳐져 보이게 됨.

#### [BUG_FIX] : 🐛 09/07일 ContactListFragment.kt 에서 onItemSelected의 item List의 holder를 재 생성 하지 못한 것을 수정하였다.
