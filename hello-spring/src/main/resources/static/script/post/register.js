const subCtgry = document.getElementById('ctgry');
const subCtgrys = document.querySelectorAll('#ctgry option');

function changeSelectOption() {
  const mainCtgry = document.getElementById('main-category');

  mainCtgry.addEventListener('change', e => {
    subCtgry.disabled = false;
    subCtgrys.forEach(item => {
      if(e.target.value != item.dataset.parent) item.classList.add('none');
      else item.classList.remove('none');
    });
    subCtgry.value = Array.from(subCtgrys).find(item => !item.classList.contains('none')).value;
  })
}

function changeParentOption() {
    const mainCtgryOptions = document.querySelectorAll('#main-category option');

    let index = subCtgry.options[subCtgry.selectedIndex].dataset.parent;
    for(let parentEl of mainCtgryOptions) {
        if(parentEl.dataset.set == parseInt(index)) parentEl.selected = true;

        for(let childEl of subCtgrys) {
            if(parentEl.value != childEl.dataset.parent) childEl.classList.add('none')
            else childEl.classList.remove('none');

        }

    }
}


function submitRegister() {
     const form = document.getElementById('submit-form');
     const subCtgry = document.getElementById('ctgry');
     const title = document.getElementById('title');
     const content = document.getElementById('content');

     form.addEventListener('submit', e => {
        if(subCtgry.value === 'none') {
            e.preventDefault();
            alert('카테고리를 선택해주세요');
            subCtgry.focus();
        } else if(title.value === '') {
            e.preventDefault();
            alert('제목을 입력해주세요');
            title.focus();
        }  else if(content.value === '') {
            e.preventDefault();
            alert('내용을 입력해주세요');
            content.focus();
        }
     });
}
export {
    changeSelectOption,
    changeParentOption,
    submitRegister
}