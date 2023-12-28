
<br>
<br>
 <div align="center">
  <img src="https://img.shields.io/badge/Java-4B4B77?style=flat&logo=java&logoColor=white"/>
   <img src="https://img.shields.io/badge/Apache Tomcat-F8DC75?style=flat&logo=Apache Tomcat&logoColor=black"/>
  <img src="https://img.shields.io/badge/MariaDB-003545?style=flat&logo=MariaDB&logoColor=white"/>
  <img src="https://img.shields.io/badge/html-E34F26?style=flat&logo=html5&logoColor=white"/>
  <img src="https://img.shields.io/badge/css-1572B6?style=flat&logo=css3&logoColor=white"/>
 </div>
<br><br>

## 🙇🏻‍♂️ 프로젝트 소개
VOUGE는 패션을 주제로 관심이 많은 사람들이 모여 다양한 정보 공유 및 소통할 수 있는 커뮤니티 입니다.

<br><br>

## 🛠️ Tech
**1. SpringBoot**<br>
**2. thymeleaf**<br>
**3. AWS S3**<br>
**4. MariaDB**<br>
**5. HTML / CSS**<br>
**6. JavaScript**<br>
**7. fontAssome**<br>
**8. SummerNote**<br>

<br><br>

## ❗️ Function
**1. 회원가입 및 로그인**<br>
 >*사용자는 회원가입을 통해 VOGUE에 가입하고, 로그인하여 자신의 계정으로 접속할 수 있습니다* <br>
 
 ![슬라이드5](https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/82b10393-108e-4127-8098-a5ee4af23e9e)
 ![슬라이드6](https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/50cc4a71-aa00-4da7-b4a9-ac29137dbe8c)
 ![슬라이드7](https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/0de2288e-69c7-478c-b955-66bfe4d2f2e7)


**2. 개인정보 수정**<br>
 >*사용자는 개인정보를 수정할 수 있으며, AWS의 S3 서비스를 통해 프로필 이미지를 업데이트 할 수 있습니다.* <br>
 
 ![슬라이드8](https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/e0d42c04-2b4c-4c05-8602-d30ae3f87a2b)
 ![슬라이드9](https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/12504106-5f69-489c-bb57-8f078f8d006e)
 ![슬라이드10](https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/149d5855-995c-4bbd-8abe-7adb4f5376c7)

**3. 글 목록**<br>
 >*사용자는 네이게이션바를 클릭하여 카테고리별 글 목록을 확인할 수 있습니다.* <br>
 
 ![슬라이드13](https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/45261335-ef67-412a-a7e8-3b1fef02b049)
 ![슬라이드14](https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/822f9e7d-5207-4179-88ae-afe508d4fcb8)


**4. 댓글**<br>
 >*댓글 작성자와 현재 세션에 저장된 사용자가 같을 시 수정 및 삭제 기능을 사용할 수 있습니다.* <br>
 
![슬라이드12](https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/65755290-f65a-4945-a951-2f4e772afaca)

**5. 글쓰기**<br>
 >*사용자가 로그인 상태라면 게시글을 작성할 수 있습니다.* <br>
 
 ![슬라이드11](https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/5d1b0614-be77-4752-b4be-a59087b31fed)

<br><br>

## 💿 Database
**1. MEMBER**<br> 
>*사용자의 정보가 저장된 테이블*<br>

| Column     | DataType            | NotNull | Default           | Description        | Key    |
|------------|---------------------|---------|-------------------|--------------------|--------|
| ID         | INT(4)              |   O     |         X         | 사용자의 고유 값       | PRIMARY |
| EMAIL      | VARCHAR(30)         |   O     |         X         | 이메일               |         |
| PASSWORD   | VARCHAR(255)        |   O     |         X         | 비밀번호             |          |
| NICKNAME   | VARCHAR(30)         |   O     |         X         | 닉네임              |         |
| GRADE      | VARCHAR(30)         |   X     |       BASIC       | 등급                |         |
| PROFILE    | LONGTEXT            |   X     | /image/non-profile.png | 프로필 이미지    |         |
| VISITED_DATE | INT(4)            |   X     |         0         | 방문횟수             |         |
| CREATED_AT | TIMESTAMP           |   X     | CURRENT_TIMESTAMP | 가입일              |        |

<br>

**2. MAIN_CTGRY**<br> 
>*메인 카테고리 테이블* <br>

| Column     | DataType            | NotNull | Default           | Description        | Key    |
|------------|---------------------|---------|-------------------|--------------------|--------|
| 	ID        | INT(2)              |   O     |         X         | 메인 카테고리 고유 값   | PRIMARY |
| NAME       | VARCHAR(30)         |   O     |         X         | 제목                |         |


<br>

**3. SUB_CTGRY**<br> 
>*서브 카테고리 테이블* <br>

| Column     | DataType            | NotNull | Default           | Description        | Key    |
|------------|---------------------|---------|-------------------|--------------------|--------|
| ID         | INT(2)              |   O     |         X         | 서브 카테고리 고유 값   | PRIMARY |
| NAME       | VARCHAR(30)         |   O     |         X         | 제목                |         |
| PARENT     | INT(2)              |   O     |         X         | 분류                | FOREIGN |

<br>

**4. POST**<br> 
>*게시글이 저장된 테이블* <br>

| Column     | DataType            | NotNull | Default           | Description        | Key    |
|------------|---------------------|---------|-------------------|--------------------|--------|
| ID         | INT(4)              |   O     |         X         | 게시글 고유 값         | PRIMARY |
| TITLE      | VARCHAR(60)         |   O     |         X         | 제목                 |         |
| CONTENT    | LONGTEXT            |   O     |         X         | 내용                 |         |
| PREPEND    | VARCHAR(10)         |   X     |         X         | 말머리               |         |
| HIT        | INT(4)              |   X     |         0         | 방문 횟수             |         |
| CREATED_AT | TIMESTAMP           |   X     | CURRENT_TIMESTAMP | 작성일               |         |
| PUBLIC_OPTION   | CHAR(1)        |   X     |         Y         | 공개 옵션             |         |
| COMMENT_OPTION  | CHAR(1)        |   X     |         Y         | 댓글 허용 옵션        |          |
| CTGRY      | INT(2)              |   O     |         X         | 카테고리             | FOREIGN |
| WRITER     | INT(4)              |   O     |         X         | 작성자              | FOREIGN |  

<br>

**5. COMMENT**<br> 
>*댓글이 저장된 테이블*<br>

| Column     | DataType            | NotNull | Default           | Description        | Key    |
|------------|---------------------|---------|-------------------|--------------------|--------|
| ID         | INT(2)              |   O     |         X         | 댓글의 고유 값         | PRIMARY |
| CONTENT    | LONGTEXT            |   O     |         X         | 내용                |          |
| CREATED_AT | TIMESTAMP           |   X     | CURRENT_TIMESTAMP | 생성일               |         |
| WRITER     | INT(2)              |   O     |         X         | 사용자 고유 값         | FOREIGN |
| POST       | INT(2)              |   O     |         X         | 게시글 고유 값         | FOREIGN |

<br>

**6. LIKED**<br> 
>*개시글의 좋아요 정보가 저장된 테이블* <br>

| Column     | DataType            | NotNull | Default           | Description        | Key    |
|------------|---------------------|---------|-------------------|--------------------|--------|
| ID         | INT(2)              |   O     |         X         | 좋아요 고유 값        | Primary |
| POST       | INT(2)              |   O     |         X         | 게시글 고유 값        | FOREIGN |
| MEMBER     | INT(2)              |   O     |         X         | 사용자 고유 값        | FOREIGN |

<br><br>

## 🖥️ Views

**1. index**<br> 
 - 프로젝트의 메인화면입니다. 상단의 LOGIN, JOIN 버튼을 클릭하여 로그인 및 회원가입 할 수 있습니다.
 - header과 aside는 `thymeleaf`의 replace 기능을 사용하여 모든 화면에서 분리된 html 파일을 불러옵니다.
 - aside의 카테고리와 메인 이미지 하단의 글 목록을 통해 게시글 목록으로 이동할 수 있습니다.
<img width="1103" alt="Index01" src="https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/9f999a9f-5624-4276-b2a6-79053f10e108">

<br><br>

 **2. Join / Login**<br> 
  - 회원가입 화면입니다.
<img width="1106" alt="join" src="https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/f444cd05-47a3-4f57-8b7c-bc128f9f43c2">

<br><br>

  - 로그인 화면입니다.
  - 로그인 후 index 화면으로 redirect 됩니다.
  - aside의 상단에 `로그인 후 이용해주세요.` 멘트가 사용자의 프로필로 변경되고 header에 로그아웃 버튼이 활성화 됩니다.
<img width="1090" alt="login" src="https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/c23fbbad-14a6-4a19-8bd0-0cf8376453d4">
<img width="1116" alt="index02" src="https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/4cc2a9e9-4673-4b6f-b1c4-9cff6b71394a">

<br><br>

 **3. Post list**<br> 
  - `패션정보` 카테고리의 게시글 목록입니다.
  - `[]` 안에 숫자를 통해 몇개의 댓글이 있는지 확인 할 수 있으며, 작성자, 작성일, 조회수, 좋아요 개수를 확인할 수 있습니다.
  - `페이지네이션`을 사용하여 구현하였습니다.
  - 검색폼을 통해 게시글 및 작성자를 검색할 수 있습니다.
<img width="1105" alt="postlist" src="https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/6c8c4d08-9f46-44e9-a63a-f34e115d8a8a">

<br><br>

 **4. Post detail**<br> 
  - 게시글의 디테일 화면입니다.
  - 상단의 카테고리 이름을 통해 해당 카테고리로 이동할 수 있습니다.
  - 작성자의 닉네임을 클릭 시 작성자의 프로필로 이동할 수 있습니다.
  - `URL 복사`를 클릭하면 현재 페이지의 URL을 복사할 수 있습니다.
  - 하단으로 스크롤하면 해당 게시글의 댓글을 확인할 수 있습니다.
<img width="1098" alt="detail" src="https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/3561485b-a847-4baa-95fc-4afe0f064f6f">

<br><br>

 - 로그인된 사용자가 현재 게시글에 `좋아요`를 눌렀을 경우 `backgroud`가 있는 하트가 보여집니다.
 - 로그인 세션이 없거나 `좋아요`를 누르지 않은 경우 `border`가 있는 하트가 보여집니다. 
<img width="1098" alt="detailLike" src="https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/51b18fa0-0f0f-471d-a9dd-22e008e9b357">
<img width="1098" alt="detailNonlike" src="https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/62a4c4f1-0886-4338-8414-fc9ecedb5c36">


<br><br>

 **5. Comment**<br> 
  - 게시글의 디테일 화면에서 스크롤하여 확인할 수 있는 댓글 목록입니다.
  - 작성자의 닉네임을 클릭 시 작성자의 프로필로 이동할 수 있습니다.
  - `ajax`를 통해 댓글이 5개씩 보여지며, `+` 버튼을 통해 남아 있는 게시글을 5개씩 불러올 수 있습니다.

 <img width="1098" alt="comment" src="https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/9e3d72ee-cc84-4bbf-94d5-d97c9fb629c8">

<br><br>

  - 댓글 작성자와 현재 세션에 저장된 사용자가 같을 시 수정 및 삭제를 할 수 있는 `hidden-button`이 활성화됩니다.
    
<img width="1098" alt="commentHidden" src="https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/e1063baa-fa73-4cac-8e24-218644bfa695">

  - `수정` 버튼을 클릭하면 현재 댓글을 수정할 수 있는 `form`이 활성화되며, 수정 및 삭제 또한 `ajax`로 구현하였습니다.

<img width="1098" alt="commentUpdate" src="https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/cc64c41b-31f5-43bc-bd93-042f4f728e73">

<br><br>

 **6. Add post**<br> 
  - 게시글을 작성할 수 있는 화면입니다.
  - 로그인 상태가 아닌 경우 해당 페이지에 접근할 수 없습니다.
  - 로그인한 회원의 등급이 `Admin`이 아닐 경우 특정 카테고리와 말머리를 선택할 수 없습니다.
  - 게시글의 공개 옵션 및 댓글 허용 옵션을 선택할 수 있습니다.
<img width="1051" alt="addPost" src="https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/c3228f56-9efe-4abd-8447-e8801c39db17">

<br><br>

 - 댓글 허용 옵션이 체크 되지 않은 경우 해당 게시글 댓글 화면입니다.
 - `댓글이 허용되지 않는 게시글입니다.` 라는 안내 멘트가 출력됩니다.  
<img width="1051" alt="commentOptionNon" src="https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/18f96df6-e783-47dd-b850-ee54cd58c8b0">

<br><br>

 **7. Profile**<br> 
  - 사용자의 프로필 화면입니다.
  - `ajax`와 `페이지네이션`을 통해 구현하였습니다.
  - 사용자가 작성한 글과 댓글단 글을 확인할 수 있습니다.
  - 현재 세션에 로그인된 사용자와 현재 페이지의 사용자가 일치하다면 공개옵션이 'N'인 작성글도 확인할 수 있습니다.
  - 작성글 입니다.
<img width="1069" alt="profilePostList" src="https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/39330e6d-3875-4cb6-9231-36c333adb1a2">

<br><br>

 - 댓글단 글 입니다.
 - 두개의 `table` 태그를 사용한 것이 아닌 하나의 테이블에서 `javascript`를 통해 동적으로 사용하였습니다.
<img width="1075" alt="profileCommentList" src="https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/5bdf4800-3266-4bb0-b2b6-de8047953ff7">

