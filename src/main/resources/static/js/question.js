
// 삭제

const deleteButton = document.getElementById('delete-btn');
console.log("실행됨")

if (deleteButton) {
    deleteButton.addEventListener('click', (event) => {
        const questionId = document.getElementById('question-id').value;
        fetch(`/api/questions/${questionId}`, {
            method: 'DELETE'
        })
        .then(() => {
            alert('삭제가 완료되었습니다.');
            location.replace('/questions');
        });
    });
}

// 수정
const modifyBtn = document.getElementById('modify-btn');

if (modifyBtn) {
    modifyBtn.addEventListener('click', (e) => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/api/questions/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title : document.getElementById('title').value,
                content : document.getElementById('content').value,
            })
        })
        .then(() => {
            alert("수정이 완료되었습니다.");
            location.replace(`/questions/${id}`);
        })
    })
}

// 생성
const createBtn = document.getElementById('create-btn');
createBtn.addEventListener('click', (event) => {
    if (createBtn) {
        fetch(`/api/questions`, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title : document.getElementById('title').value,
                content : document.getElementById('content').value,
            })
        })
        .then(() => {
            alert('새로운 글을 작성했습니다.');
            location.replace('/questions');
        }

        )
    }
})