// 게시글의 총 개수
const postTotalCount = document.getElementById('post-count').innerText;
// 댓글의 총 개수
const commentTotalCount = document.getElementById('comment-count').innerText;
// 요청 개수
const requestIndex = 10;
// 사용자의 ID
const url = window.location.href;
const lastSlashIndex = url.lastIndexOf('/');
const userPK = lastSlashIndex !== -1 ? url.slice(lastSlashIndex + 1) : null;
// 페이징
let numOfPages;

// ----------------------------------------------------
const postBtn =  document.getElementById('post-btn');
const commentBtn =  document.getElementById('content-btn');



const tableBody = document.getElementsByTagName('tbody')[0];
const paginationWrap =  document.getElementById('pagination-wrap');
const emptyBox = document.getElementById('empty-box');

// ----------------------------------------------------
function setNumOfPages(data) {
    numOfPages = Math.ceil(data / requestIndex);
}
// ----------------------------------------------------
function setRequestHandler() {
   paginationWrap.innerHTML = '';
   for(let i=1; i<=numOfPages; i++) {
      // 페이지 요청 시 첫번째 페이징 요소에 current-page 클레스 추가
      let actionClass = (i == 1) ? 'current-page' : '';

      let template = `
        <a class="bold text-center inline-block margin-x-s pointer request-btn ${actionClass}"">
            ${i}
        </a>
      `
     paginationWrap.insertAdjacentHTML('beforeend', template);
    }
  }

// ----------------------------------------------------
function dataBindingHandler(result, element, emptyElement) {

    const tableHead = document.getElementsByTagName('th');

    element.innerHTML = '';
    emptyElement.innerHTML = '';


    if(result != '' || result.length) {
        result.forEach(list => {

            let template;
            let commentCount = list.count ? ' [' + list.count + ']': '';
            if(postBtn.classList.contains('current-option')) {
                  // th의 innerHTML 및 class 제어
                  for(let i=0; i<tableHead.length; i++) {
                      if(!i) tableHead[i].innerText = '제목';
                      else tableHead[i].classList.remove('none');
                  }

                  // 말머리와 댓글 수에 따라 값 반환
                  let prepend = list.prepend ? list.prepend : '';
                  let prependClass = list.prepend ? ' post-notice-title' : ''

                  // response의 데이터바인딩
                  template = `
                     <tr>
                         <td class="text-left">
                             <a href="/post/detail/${list.id}" class="post-title color-666">
                                 <span>${list.title}</span>
                                 <span class="color-red">${commentCount}</span>
                             </a>
                         </td>
                          <td class="post-date">${list.created_at.slice(0, 10)}</td>
                          <td class="post-hit">${list.hit}</td>
                     </tr>
                  `
            } else {
                   // th의 innerHTML 및 class 제어
                   for(let i=0; i<tableHead.length; i++) {
                       if(!i) tableHead[i].innerText = '댓글';
                       else tableHead[i].classList.add('none');
                   }
                // response의 데이터바인딩
                template = `
                  <tr>
                    <td class="text-left">
                        <a href="/post/detail/${list.id}" class="post-title color-666">
                            <p class="color-bk">${list.content}</p>
                            <p class="font-size-s">${list.created_at.slice(0, 10)}</p>
                            <p class="font-size-s">
                                ${list.title}
                                <span class="color-red font-size-s">${commentCount}</span>
                            </p>
                        </a>
                    </td>
                  </tr>
                `
            }
          element.insertAdjacentHTML('beforeend', template);
        })
    } else {
        emptyElement.innerHTML = '';
        emptyElement.innerHTML = '작성글이 존재하지 않습니다.';
    }
  }
// ----------------------------------------------------
 async function requestPostData(url) {
    return fetch(url).then(response => response.json());
 }
// ----------------------------------------------------
async function fetchedPostData() {

    try {
     let result = await requestPostData(`/post/member/${userPK}/0`);

     setNumOfPages(postTotalCount);
     setRequestHandler();
     paginationHandler();
     dataBindingHandler(result, tableBody, emptyBox);
    } catch(err) {
        alert(`error!! ${err.message}`);
    }
}

async function fetchedCommentData() {

    try {
        let result = await requestPostData(`/comment/member/${userPK}/0`);
        setNumOfPages(commentTotalCount);
        setRequestHandler();
        paginationHandler();
        dataBindingHandler(result, tableBody, emptyBox);
    } catch(err) {
            alert(`error!! ${err.message}`);
    }
}
// ----------------------------------------------------
// pagination
   function paginationHandler() {
      const requestBtn = document.getElementsByClassName('request-btn');

      for(let i=0; i<requestBtn.length; i++) {
          requestBtn[i].addEventListener('click', async (e) => {
              // 현재 페이지에서 중복 ajax 요청 제어
              if (!requestBtn[i].classList.contains('current-page')) {
                  // 현재 요청한 버튼이 아닌 나머지 버튼의 current-page 클레스 삭제
                  for(let j=0; j<requestBtn.length; j++) requestBtn[j].classList.remove('current-page');
                  // 현재 요청한 버튼에 current-page 클레스 추가
                  requestBtn[i].classList.add('current-page');

                  try {
                      let result;

                      if(postBtn.classList.contains('current-option')) {
                            result = await requestPostData(`/post/member/${userPK}/${i * requestIndex}`);
                      }
                      else if(commentBtn.classList.contains('current-option')) {
                            result = await requestPostData(`/comment/member/${userPK}/${i * requestIndex}`);
                      }
                      dataBindingHandler(result, tableBody, emptyBox);

                  } catch(err) {
                      alert(`error !! ${err.message}`);
                  }
              } else e.preventDefault();
          });
      }
    }
// ----------------------------------------------------
function clickToggleHandler(btn1, btn2, func) {

    btn1.addEventListener('click', e => {
        if(btn1.classList.contains('current-option')) e.preventDefault();
        else {
            btn2.classList.remove('current-option');
            btn1.classList.add('current-option');
            func();
        }
    })
}
// ----------------------------------------------------
function btnListener() {
    clickToggleHandler(postBtn, commentBtn, fetchedPostData);
    clickToggleHandler(commentBtn, postBtn, fetchedCommentData);
}
// ----------------------------------------------------
 export {
    setRequestHandler,
    fetchedPostData,
    paginationHandler,
    btnListener,
 }