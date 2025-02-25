
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