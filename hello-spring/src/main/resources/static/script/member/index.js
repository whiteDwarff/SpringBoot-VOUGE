import {
	validateEmail,
	validatePwd,
	pwdCheck } from './memberValidation.js';

function submitLoginInfo() {
     document.getElementById('login-form').addEventListener('submit', e => {
     let email =  document.getElementById('email');
     let pwd =  document.getElementById('password');

     if(!validateEmail(email.value)) {
        e.preventDefault();
        alert('올바른 이메일 형식이 아닙니다.');
        email.focus();
     }  else if(!validatePwd(pwd.value)) {
        e.preventDefault();
        alert('비밀번호는 대문자와 특수문자, 숫자를 포함한 8-15자 이내로 입력해주세요.');
        pwd.focus();
     }
    });
}

function submitMemberInfo(form, nickname, pwd, pwdChk, ohterOption='') {
     form.addEventListener('submit', e => {
        if(!nickname.value || nickname.value === '') {
            e.preventDefault();
            alert('닉네임을 입력해주세요.');
            nickname.focus();
        } else if(nickname.value.length < 2 || nickname.value.length > 15) {
            e.preventDefault();
            alert('닉네임은 2글자에서 10글자 사이로 입력해주세요.');
            nickname.focus();
        } else if(!validatePwd(pwd.value)) {
            e.preventDefault();
            alert('비밀번호는 대문자와 특수문자, 숫자를 포함한 8-15자 이내로 입력해주세요.');
            pwd.focus();
        } else if(pwd.value != pwdChk.value) {
            e.preventDefault();
            alert('비밀번호가 일치하지 않습니다.');
            pwdChk.focus();
        } else if(ohterOption) {
            console.log(ohterOption.type);
            if(ohterOption.type == 'file' && !ohterOption.value) {
                e.preventDefault();
                alert('프로필 이미지를 선택해주세요.');
            } else if(ohterOption.type == 'email' && !validateEmail(ohterOption.value)) {
              e.preventDefault();
              alert('올바른 이메일 형식이 아닙니다.');
              ohterOption.focus();
             }
        }
    });
}

export {
    submitMemberInfo,
    submitLoginInfo,
}