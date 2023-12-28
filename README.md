[header](https://capsule-render.vercel.app/api?type=waving&color=timeGradient&text=웹개발자%20성장을%20위한%20동영상%20RoadMap%20프로그램%20설계%20및%20구현🚀&animation=twinkling&fontSize=23&fontAlignY=40&fontAlign=60&height=250&width=1325&align=center)

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
| WRITER     | INT(2)              |   O     |         X         | 작성자               |         |
| POST       | INT(2)              |   O     |         X         | 게시글 고유 값         |         |

<br>

**6. LIKED**<br> 
>*개시글의 좋아요 정보가 저장된 테이블* <br>

| Column     | DataType            | NotNull | Default           | Description        | Key    |
|------------|---------------------|---------|-------------------|--------------------|--------|
| ID         | INT(2)              |   O     |         X         | 좋아요 고유 값        | Primary |
| POST       | INT(2)              |   O     |         X         | 게시글 고유 값         |         |
| MEMBER     | INT(2)              |   O     |         X         | 사용자 고유 값         |         |

<br><br>
