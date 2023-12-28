const getBtn = document.getElementById('get-comment-btn');
const commentWrap = document.getElementById('comment-wrap');
const ascSortBtn = document.getElementById('asc-option-btn');
const descSortBtn = document.getElementById('desc-option-btn');
// 사용자의 닉네임
const nickname = document.querySelector('#profile h4').innerText;
// 게시글의 PK
const url = window.location.href;
const lastSlashIndex = url.lastIndexOf('/');
const id = lastSlashIndex !== -1 ? url.slice(lastSlashIndex + 1) : null;

 let firstValue = 0;       // 요청 갯수
 let sortOption = 'ASC';   // 정렬 옵션
 let totalCount;           // DB의 데이터 갯수
 let likeState;            // 사용자의 좋아요 상태 변수
 let memberPK;


// 게시글 삭제 클릭 시 사용자에게 확인 여부 확인
function deletePost() {
    const deleteBtn = document.getElementById('delete-btn');
    deleteBtn.addEventListener('click', e => {
        if(!confirm('게시글을 삭제하시겠습니까?')) e.preventDefault();
    })
}

// 게시글 수정 및 삭제 버튼 활성화
function toggleHiddenWrapHandler() {
     const submitBtn = document.getElementById('update-btn');
     const hiddenWrap = document.getElementById('hidden-wrap');

      submitBtn.addEventListener('click', () => {
         hiddenWrap.classList.toggle('none');
      });
}

// Spring Model의 데이터 셋팅
function setModelData(getTotalCount, getLikeState, getMemberPK) {
    totalCount = getTotalCount;
    likeState = getLikeState;
    memberPK = getMemberPK;
}

// 더보기 버튼 클릭 시 데이터 5개씩 불러오기
function moreCommentOption() {
  getBtn.addEventListener('click', () => fetchedCommentList());
}

// 버튼 클릭 시 정렬 옵션 및 이벤트 핸들러 발생 함수화
function sortHandelr(firstOption, lastOption, sort) {
  firstOption.addEventListener('click', e => {
    sortOption = sort;
    firstValue = 0;
    e.target.classList.add('current_option');
    lastOption.classList.remove('current_option');
    if(getBtn.classList.contains('none')) {
      getBtn.classList.remove('none');
    }
    commentWrap.innerHTML = '';
    fetchedCommentList();
  });
}

// 버튼 클릭 시 정렬 옵션 및 이벤트 핸들러 발생 실행
function fetchedSortCommentOption() {
  sortHandelr(ascSortBtn, descSortBtn, 'ASC');
  sortHandelr(descSortBtn, ascSortBtn, 'DESC');
}

// 날짜 포맷 생성
function formatDateTime(dateTimeString) {
  const options = { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit', second: '2-digit', timeZone: 'Asia/Seoul' };
  return new Intl.DateTimeFormat('ko-KR', options).format(new Date(dateTimeString));
}

//  ajax를 통한 비동기 로직 ---------------------------------------
// 댓글 조회
async function fetchedCommentList() {
  if(totalCount) {
      fetch(`/comment/getComment/${id}/${sortOption}/${firstValue}`)
        .then(response => response.json())
        .then(data => {
         data.forEach(comment => {
           // 세션에 저장된 사용자가 없거나 댓글의 작성자가 본인이 아닌  경우
           if(comment.nickname != nickname.replace(/"/g, '')) {
             let template = `
                <li class="padding-y-s border-bottom-ccc post-comments">
                  <div class="flex align-center">
                        <img src="${comment.profile}" class="header-profile">
                        <div>
                            <a href="/member/profile/${comment.WRITER}" class="hover-underline">
                                <span class="display-name bold">${comment.nickname}</span>
                            </a>
                            <span class="grade">${comment.grade}</span>
                          <div>
                            <p class="margin-y-s content">${comment.CONTENT}</p>
                            <span class="font-size-s color-grey">${formatDateTime(comment.CREATED_AT)}</span>
                          </div>
                        </div>
                  </div>
                </li>
              `
              commentWrap.insertAdjacentHTML('beforeend', template);
            // 세션에 저장된 사용자와 게시글의 작성자가 같은 경우
           } else {
              let template = `
                  <li class="padding-y-s border-bottom-ccc post-comments" data-id="${comment.ID}">
                    <div class="flex align-center">
                      <img src="${comment.profile}" class="header-profile">
                      <div class="comment-wrap comment-content-wrap">
                        <div class="flex between">
                          <div>
                            <a href="/member/profile/${comment.WRITER}" class="hover-underline">
                                <span class="display-name bold">${comment.nickname}</span>
                            </a>
                            <span class="grade">${comment.grade}</span>
                          </div>
                          <div>
                            <div class="comment-update-wrap pointer">
                              <i class="fa-solid fa-ellipsis-vertical color-grey comment-update-btn"></i>
                              <div class="comment-hidden-wrap none">
                                <a class="comment-modify-btn hidden-btn block">수정</a>
                                <a class="comment-delete-btn hidden-btn block" data-id="${comment.ID}">삭제</a>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div>
                          <p class="margin-y-s content" data-id="${comment.ID}">${comment.CONTENT}</p>
                          <span class="font-size-s color-grey">${formatDateTime(comment.CREATED_AT)}</span>
                        </div>
                      </div>
                      <div class="insert-comment-area full-width none">
                        <form class="comment-submit-form">
                          <p class="bold">${comment.nickname}</p>
                          <textarea class="block" rows="6" data-id="${comment.ID}">${comment.CONTENT}</textarea>
                          <div class="comment-button-wrap text-right">
                            <span class="comment-edit-btn color-grey bold pointer">취소</span>
                            <button type="submit" class="add-comment-btn margin-x-s pointer">제출</button>
                          </div>
                        </form>
                      </div>
                  </li>
              `
            commentWrap.insertAdjacentHTML('beforeend', template);
            }
          })
          if((firstValue += 5) >= totalCount) getBtn.classList.add('none');

          // 댓글의 수정, 삭제 버튼 <i>
          const commentUpdateBtn = document.getElementsByClassName('comment-update-btn');
          // 댓글의 컨텐츠
          const commentContentWrap = document.getElementsByClassName('comment-content-wrap');
          // <i> 태그 자식인 <div> 태그
          const commentHiddenWrap = document.getElementsByClassName('comment-hidden-wrap');
          // 댓글 수정 버튼
          const commentModifyBtn = document.getElementsByClassName('comment-modify-btn');
          // 댓글 수정 폼의 부모 <div>
          const commentSubmitForm = document.getElementsByClassName('insert-comment-area');
          // 댓글 수정 폼
          const updateForm = document.getElementsByClassName('comment-submit-form');
          // 댓글 수정 <textarea>
          const updateInput = document.querySelectorAll('.comment-submit-form textarea');
          // 댓글 수정 취소 버튼
          const commentEditBtn = document.getElementsByClassName('comment-edit-btn');
          // 댓글 삭제 버튼
          const commentDeleteBtn = document.getElementsByClassName('comment-delete-btn');


          function toggleClassByElement(index) {
               commentSubmitForm[index].classList.add('none');
               commentContentWrap[index].classList.remove('none')
               commentHiddenWrap[index].classList.add('none');
          }


          for(let i=0; i<commentUpdateBtn.length; i++) {
              // 댓글 수정 삭제 버튼 활성화
              commentUpdateBtn[i].addEventListener('click', e => {
                if(commentHiddenWrap[i].classList.contains('none')) commentHiddenWrap[i].classList.remove('none');
                else commentHiddenWrap[i].classList.add('none');
              });
              // 수정 버튼 클릭 시 <textarea> 활성화
              commentModifyBtn[i].addEventListener('click', e => {
                commentContentWrap[i].classList.add('none');
                commentSubmitForm[i].classList.remove('none');
              });
              // 취소 버튼 클릭 시 <textarea> 비활성화
              commentEditBtn[i].addEventListener('click', e=> {
                toggleClassByElement(i);
              });
              // 댓글 수정
              updateForm[i].addEventListener('submit', e => {
                   e.preventDefault();
                   if(updateInput[i].value == '') {
                      alert('댓글을 입력해주세요.');
                      updateInput[i].focus();
                   } else {
                      let currentId = updateInput[i].dataset.id;
                      let newValue =  updateInput[i].value;
                      let comment = document.getElementsByClassName('content');

                      updatedComment(currentId, newValue);
                      toggleClassByElement(i);

                      for(let item of comment)
                        if(item.dataset.id == currentId) {
                            item.innerHTML = "";
                            item.innerHTML = newValue;
                            break;
                      }
                   }
              });
              // 댓글 삭제
              commentDeleteBtn[i].addEventListener('click', e => {
                   if(!confirm('댓글을 삭제하시겠습니까?')) e.preventDefault();
                   else {
                      let contents = document.getElementsByClassName('post-comments');
                      let commentCount = document.getElementsByClassName('comment-count');
                      let currentId = e.target.dataset.id;

                      // ajax를 통해 삭제 요청
                      deleteComment(currentId);
                      // 데이터 삭제 후 해당 삭제한 요소 숨김
//                      for(let item of contents)
//                        if(item.dataset.id && item.dataset.id == currentId) {
//                              item.classList.add('none');
//                              break;
//                        }
                        for(let j=0; j<contents.length; j++)
                            if(contents[j].dataset.id && contents[j].dataset.id == currentId) {
                                  contents[j].classList.add('none');
                                  if(j) contents[j-1].classList.remove('border-bottom-ccc');
                                  break;
                            }
                      for(let item of commentCount) item.innerText = parseInt(item.innerText) - 1;

                   }
              });
            } // 이벤트 리스너의 for문
         })
        .catch(err => console.log(err));
  // 조회할 게시글이 없을 경우
  } else {
    getBtn.classList.add('none');
    let template = `
      <span class="block margin-y-m color-666">등록된 댓글이 존재하지 않습니다.</span>
    `
    commentWrap.insertAdjacentHTML('beforeend', template);
  }
}

// 댓글 수정
function updatedComment(id, content) {
    const data = {
        content,
    }
    fetch(`/comment/update/${id}`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    })
    .then(resp => {
        if(resp.status == 200) alert('댓글 수정이 완료되었습니다.');
        else alert('댓글 수정에 실패하였습니다.');
    })
    .catch(err => alert('서버와의 통신이 불안정합니다.'));
}
// 댓글 삭제
function deleteComment(id) {
    fetch(`/comment/delete/${id}`, {
        method: "DELETE"
    })
    .then(resp => {
        if(resp.ok) alert('댓글이 삭제되었습니다.');
        else alert('댓글 삭제에 실패하였습니다.');
    })
    .catch(err => alert('서버와의 통신이 불안정합니다.'));
}

function likeToggleHandler() {
    const likeBtn = document.getElementById('like-btn');
    likeBtn.addEventListener('click', e => {

        let icon = likeBtn.getElementsByTagName('i')[0];
        let likeCount = document.getElementById('like-count');

        if(!memberPK) e.preventDefault();
        else {
            // 좋아요 취소
            if(likeState) {
                fetch(`/post/delete/${id}/${memberPK}`, {method : 'DELETE'})
                    .then(resp => {
                        if(resp.ok) {
                           likeCount.innerText = parseInt(likeCount.innerText) - 1;
                           icon.classList.remove('fa-solid');
                            likeState = false;
                        }
                    })
                    .catch(err => console.log(err));
            // 좋아요
            } else {
                fetch(`/post/like/${id}/${memberPK}`)
                    .then(resp => {
                        if(resp.ok) {
                            likeCount.innerText = parseInt(likeCount.innerText) + 1;
                            icon.classList.add('fa-solid');
                            likeState = true;
                        }
                    })
                    .catch(err => console.log(err));
              }
        }
    });
}

function copyClipBoard() {

    document.getElementById('clip-board').addEventListener('click', () => {
        let url = location.href;
        let dummy = document.createElement('input');

        document.body.appendChild(dummy);
        dummy.value = url;
        dummy.select();
        document.execCommand('copy');
        alert('URL을 클립보드에 복사했습니다.');
        document.body.removeChild(dummy);
    })
}

export {
    toggleHiddenWrapHandler,
    deletePost,
    setModelData,
    fetchedCommentList,
    moreCommentOption,
    fetchedSortCommentOption,
    likeToggleHandler,
    copyClipBoard,
}

