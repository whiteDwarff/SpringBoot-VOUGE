  let totalCount;
  let numOfPages;

  const url = window.location.href;
  const lastSlashIndex = url.lastIndexOf('/');
  const ctgry = lastSlashIndex !== -1 ? url.slice(lastSlashIndex + 1) : null;

  const requestIndex = 10;

  // ----------------------------------------------------
  const paginationWrap =  document.getElementById('pagination-wrap');
  const emptyBox = document.getElementById('empty-box');
  const tableBody = document.getElementsByTagName('tbody')[0];
  // ----------------------------------------------------

  function setModelData(getTotalCount) {
    totalCount = getTotalCount;
    numOfPages = Math.ceil(totalCount / requestIndex);
  }

  function setRequestHandler() {
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

  function dataBindingHandler(result, element, emptyElement) {
    element.innerHTML = '';
    emptyElement.innerHTML = '';
    if(result.length) {
          console.log(result);
        result.forEach(list => {
          // 말머리와 댓글 수에 따라 값 반환
          let prepend = list.prepend ? list.prepend : '';
          let prependClass = list.prepend ? ' post-notice-title bold' : ''
          let commentCount = list.count ? ' [' + list.count + ']': '';

          // response의 데이터바인딩
          let template = `
             <tr>
                <td class="text-left">
                    <a href="/post/detail/${list.id}" class="post-title color-666 ${prependClass}">
                        <span>${prepend}${list.title}</span>
                        <span class="color-red">${commentCount}</span>
                    </a>
                </td>
                <td class="post-writer">
                    <a href="/member/postView/" class="color-666">${list.nickname}</a>
                </td>
                <td class="post-date">${list.created_at.slice(0, 10)}</td>
                <td class="post-hit">${list.hit}</td>
                <td class="post-like">${list.like_count}</td>
             </tr>
                             `
          element.insertAdjacentHTML('beforeend', template);
        })
    } else {
        emptyElement.innerHTML = '';
        emptyElement.innerHTML = '검색된 내용이 존재하지 않습니다.';

    }
  }

  async function requestPostData(url) {
    return fetch(`/post/${url}`).then(response => response.json());
  }

  // 페이지네이션 구현
  function responsePostData() {
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
                    let result = await requestPostData(`list/${ctgry}/${i * requestIndex}`);
                    dataBindingHandler(result, tableBody, emptyBox);
                } catch(err) {
                    alert(`error !! ${err.message}`);
                }
            } else e.preventDefault();
        });
    }
  }


 function searchList() {
      const submitBtn = document.getElementById('post-search-btn');

      submitBtn.addEventListener('click', async (e) => {
          let option =  document.getElementById('select-box').value;
          let value = document.getElementById('post-search-content').value;

          if(value == '') {
               e.preventDefault();
               alert('검색어를 입력해주세요.');
          } else {
               try {
                    let result = await requestPostData(`search/${ctgry}/${option}/${value}`);
                    document.getElementById('pagination-wrap').classList.add('none');
                    dataBindingHandler(result, tableBody, emptyBox);
               } catch (err) {
                    alert(`error !! ${err.message}`);
               }
          }
      });
  }

  export {
    setModelData,
    setRequestHandler,
    responsePostData,
    searchList,
  }