
// 파일 선택 시 이미지 썸네일 선택
const addThumbnail = () => {
    // input
    const thumbnailValue = document.getElementById('profile-img');
    // img
    const thumbnail = document.getElementsByClassName('thumbnail')[0];
    thumbnailValue.addEventListener('change', () => {
        const reader = new FileReader();
        reader.onload = ({ target }) => {
            thumbnail.src = target.result;
        }
        // 비동기 실행으로 URL을 받고 난 뒤 img의 src 속성 변경
        reader.readAsDataURL(thumbnailValue.files[0]);
    })
}

export {
    addThumbnail,
}