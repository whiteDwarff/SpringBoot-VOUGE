export default function logout() {
    const logoutBtn = document.getElementById('logout-btn');
    if(logoutBtn)
        logoutBtn.addEventListener('click', e => {
            if(!confirm('로그아웃 하시겠습니까?')) e.preventDefault();
        })
}