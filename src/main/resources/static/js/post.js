document.getElementById('editPostBtn').addEventListener('click', function() {
    document.getElementById('editPostFormID').style.display = 'block';
});

document.getElementById('saveEditedPostBtn').addEventListener('click', function() {
    // Отправка данных на сервер для редактирования поста
    fetch('/editPost', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: document.getElementById('postId').value,
            title: document.getElementById('postTitle').value,
            image: document.getElementById('postImage').files[0],
            text: document.getElementById('postText').value,
            tags: document.getElementById('postTags').value
        })
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error(error));
});

document.getElementById('likePostBtn').addEventListener('click', function() {
    // Отправка запроса на сервер для лайка поста
    fetch('/likePost', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            postId: document.getElementById('postId').value
        })
    })
        .then(response => response.json())
        .then(data => {
            document.getElementById('likesCount').innerText = data.likesCount;
        })
        .catch(error => console.error(error));
});
