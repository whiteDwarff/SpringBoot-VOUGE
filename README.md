![header](https://capsule-render.vercel.app/api?type=waving&color=timeGradient&text=웹개발자%20성장을%20위한%20동영상%20RoadMap%20프로그램%20설계%20및%20구현🚀&animation=twinkling&fontSize=23&fontAlignY=40&fontAlign=60&height=250&width=1325&align=center)

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
 
 ![슬라이드5](https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/655f6e15-c250-4ddd-98b3-2b113042f5b8)
 ![슬라이드6](https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/69654032-a2ed-4231-b867-840ef3aba940)
 ![슬라이드7](https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/1f72c9f7-5f3d-4c85-95e8-c50c3da8d8d8)

**2. 개인정보 수정**<br>
 >*사용자는 개인정보를 수정할 수 있으며, AWS의 S3 서비스를 통해 프로필 이미지를 업데이트 할 수 있습니다.* <br>
 
 ![슬라이드8](https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/a0bbeaac-6d32-46b6-99ed-4249797478d0)
 ![슬라이드9](https://github.com/whiteDwarff/SpringBoot-VOUGE/assets/115057117/f1af0845-2038-4e91-97d4-5a960faa778a)
 
**3. 질문과 답변**<br>
 >*사용자들은 질문과 답변 게시판을 통해 서로 도움을 주고 받을 수 있으며, 지식을 공유할 수 있습니다.* <br>
 
**4. 공지사항**<br>
 >*운영자는 공지사항 게시판을 통해 학습자들에게 중요한 정보와 업데이트 사항을 공지할 수 있습니다.*<br>

**5. 강의관리**<br>
 >*사용자의 등급이 Teacher인 경우 강의를 등록 및 수정할 수 있습니다.*<br>

**6. 회원관리**<br>
 >*사용자의 등급이 Owner인 경우 회원등급 관리 및 탈퇴시킬 수 있습니다.*<br>

<br><br>

## 💿 Database
**1. Member**<br> 
>*사용자의 정보가 저장된 테이블*<br>

| Column     | DataType            | NotNull | Default           | Description        | Key    |
|------------|---------------------|---------|-------------------|--------------------|--------|
| id         | int(11)             |   O     |         X         | 사용자의 고유 값  | Primary     |
| name       | varchar(15)         |   O     |         X         | 사용자의 이름    | |
| email      | varchar(30)         |   O     |         X         | 사용자의 이메일    | |
| password   | varchar(255)        |   O     |         X         | 사용자의 비밀번호   |  |
| phone_number | int(15)           |   X     |         X          | 사용자의 휴대폰 번호 |  |
| created_at | timestamp           |   O     |  current_timestamp | 가입일     | |
| rank      | varchar(1)           |   O     |       B       | 사용자의 등급    |  |

<br>

**2. Board**<br> 
>*사용자의 문의사항이 저장된 테이블*<br>

| Column     | DataType            | NotNull | Default           | Description        | Key    |
|------------|---------------------|---------|-------------------|--------------------|--------|
| id         | int(11)             |   O     |         X         | 게시글의 고유 값  | Primary     |
| title      | varchar(255)        |   O     |         X         | 게시글의 제목    | |
| content    | longtext            |   O     |         X         | 게시글의 내용    | |
| created_at | timestamp           |   X     | current_timestamp | 작성일         |  |
| hit        | int(4)              |   O     |       0           | 게시글의 조회수  |  |
| author_id  | int(11)             |   O     |         X         | 게시글 작성자의 고유 값    | Foreign |

<br>

**3. Notice**<br> 
>*관리자의 공지사항이 저장된 테이블*<br>

| Column     | DataType            | NotNull | Default           | Description        | Key    |
|------------|---------------------|---------|-------------------|--------------------|--------|
| id         | int(11)             |   O     |         X         | 공지사항의 고유 값  | Primary     |
| title      | varchar(255)        |   O     |         X         | 공지사항의 제목    | |
| content    | longtext            |   O     |         X         | 공지사항의 내용    | |
| created_at | timestamp           |   X     | current_timestamp | 작성일         |  |
| hit        | int(4)              |   O     |       0           | 공지사항의 조회수  |  |
| author_id  | int(11)             |   O     |         X         | 공지사항의 작성자    | Foreign |

<br>

**4. Comment**<br> 
>*게시글의 댓글이 저장된 테이블*<br>

| Column     | DataType            | NotNull | Default           | Description        | Key    |
|------------|---------------------|---------|-------------------|--------------------|--------|
| id         | int(11)             |   O     |         X         | 댓글의 고유 값  | Primary     |
| content    | longtext            |   O     |         X         | 댓글의 내용    | |
| created_at | timestamp           |   X     | current_timestamp | 작성일         |  |
| post       | int(11)             |   O     |         X         | 게시글의 고유 값 | Foreign |
| author_id  | int(11)             |   O     |         X         | 댓글 작성자     | Foreign |

<br>

**5. category**<br> 
>*동영상의 카테고리 정보가 저장된 테이블*<br>

| Column     | DataType            | NotNull | Default           | Description        | Key    |
|------------|---------------------|---------|-------------------|--------------------|--------|
| id         | int(11)             |   O     |         X         | 카테고리의 고유 값 | Primary     |
| discription | varchar(50)        |   O     |         X         | 카테고리의 설명   | |
| created_at | timestamp           |   X     | current_timestamp | 생성일         |  |

<br>

**6. video**<br> 
>*동영상의 정보가 저장된 테이블*<br>

| Column     | DataType            | NotNull | Default           | Description        | Key    |
|------------|---------------------|---------|-------------------|--------------------|--------|
| id         | int(11)             |   O     |         X         | 동영상의 고유 값  | Primary     |
| title      | varchar(60)         |   O     |         X         | 동영상의 내용     | |
| description |  varchar(150)      |   O     |         X         | 작성일          |  |
| url        | varchar(50)         |   O     |         X         | 동영상의 url 정보 |  |
| hit  | int(4)                    |   O     |         0         | 동영상의 조회수    |  |
| uploader_id  | int(11)           |   O     |         X         | 작성자         | Foreign |
| uploaded_at  | timestamp         |   O     |  current_timestamp| 작성일         | Foreign |
| category_id  | int(11)           |   O     |         X         | 카테고리 고유 값    | Foreign |

<br>

**7. wish**<br> 
>*사용자가 관심목록으로 저장한 동영상 정보가 저장된 테이블*<br>

| Column     | DataType            | NotNull | Default           | Description        | Key    |
|------------|---------------------|---------|-------------------|--------------------|--------|
| id         | int(11)             |   O     |         X         | 관심목록의 고유 값      |Primary|
| video_id   | int(11)             |   O     |         X         | 비디오의 고유값        |Foreign|
| member_id  | int(11)             |   O     |         X         | 사용자의 고유값        |Foreign|

<br><br>
