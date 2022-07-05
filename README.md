# First App

---

> Flow Week 3분반
> 
- 사람들과 정보를 공유할 수 있는 Android 기반 커뮤니티 어플리케이션입니다.
- 이메일과 비밀번호를 통해 회원가입 및 로그인할 수 있습니다.
- 커뮤니티원들의 연락처와 이름을 저장할 수 있습니다.
- 커뮤니티에서 자신의 관심사에 대한 게시글을 업로드, 수정, 삭제할 수 있습니다.

---

![Screenshot_20220705-200147_first_app](https://user-images.githubusercontent.com/76472415/177323484-014025df-954e-4b6f-b9b5-6cf6d43bba9d.jpg)

![Screenshot_20220705-200129_first_app](https://user-images.githubusercontent.com/76472415/177323507-1eda7805-9ae6-408a-9ae8-af9c978370d0.jpg)

![Screenshot_20220705-200109_first_app](https://user-images.githubusercontent.com/76472415/177323547-dae6a58a-81eb-4722-b417-c0478c069102.jpg)

![Screenshot_20220705-200105_first_app](https://user-images.githubusercontent.com/76472415/177323564-1ff94b71-e9f8-4c06-8c53-e9dd6b982b1d.jpg)

---

### A. 개발 팀원

- 숙명여자대학교 소프트웨어융합전공 박예나
- KAIST 전산학부 정윤석

---

### B. 개발 환경

- OS : Android (minSdk: 21, targetSdk: 31)
- Language : Kotlin
- IDE : Android Studio
- Target Device : Galaxy S10

---

### C. 어플리케이션 소개

### LOGIN

![Screenshot_20220705-200147_first_app.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e823b9ab-53ad-4f2f-aaf8-29f1a5e59e98/Screenshot_20220705-200147_first_app.jpg)

![Screenshot_20220705-200129_first_app.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1090a934-8946-4652-83cb-cbc3b0fdac8e/Screenshot_20220705-200129_first_app.jpg)

![Screenshot_20220705-200109_first_app.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e71e1647-4289-4672-9930-b5a7418e0efa/Screenshot_20220705-200109_first_app.jpg)

**Main features**

- Login
    - email과 password를 입력한 후, “LOGIN” 버튼을 통해 앱을 사용할 수 있습니다.
    - 이전에 로그인이 되어 있었던 상태일 경우 자동 로그인이 가능합니다.
- Sign up
    - “SIGN UP” 버튼을 통해 이메일 기반의 회원가입을 할 수 있습니다.

---

**기술 설명**

- Firebase를 이용하여 유저의 정보를 database에 저장할 수 있는 앱을 개발했습니다.
- 이전의 currentUser 정보가 존재할 경우, 자동 로그인을 통해 바로 메인 앱 기능을 실행할 수 있도록 합니다.

---

### TAB 1 - 연락처

**Main features**

- 연락처
    - 로컬에 저장되어 있는 연락처 정보를 불러와 목록을 볼 수 있습니다.

---

**기술 설명**

- Json 파일을 parse하여 연락처 데이터를 얻은 후 Recycler view를 이용하여 이름과 전화번호를 display하는 Phonebook tab을 구현하였습니다.

---

### TAB 2 - 이미지 갤러리

![Screenshot_20220705-200100_first_app.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/3df70ec2-02fb-4dac-ad12-aff953226a38/Screenshot_20220705-200100_first_app.jpg)

**Main features**

- 이미지 갤러리
    - 휴대폰 갤러리에 저장되어 있는 사진을 불러올 수 있습니다.

---

**기술 설명**

- 갤러리 접근 Permission을 허용하여 휴대폰 내 사진의 접근 권한을 허용했습니다.
- RecyclerView를 이용하여 가로 스크롤 갤러리를 구현하였습니다.
- 스크롤의 끝에 도달할 때 매끄러운 자석효과 애니메이션을 추가하였습니다.

---

### TAB 3 - 커뮤니티



**Main features**

- + 버튼을 통해 새로운 게시글을 작성할 수 있습니다.
- 게시글 목록을 선택하여 해당 게시글의 내용을 확인할 수 있습니다.
- “수정하기” 버튼을 통해 게시글을 수정할 수 있습니다.
- “삭제하기” 버튼을 통해 게시글을 삭제할 수 있습니다.

---

**기술 설명**

- Firebase에 게시글을 작성할 때 작성자, 작성시간, 제목, 내용이 RealTime Database에 저장됩니다.
