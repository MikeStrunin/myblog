document.getElementById('addPostBtn').addEventListener('click', function() {
    document.getElementById('addPostForm').style.display = 'block';
});

document.getElementById('savePostBtn').addEventListener('click', function() {
    // Отправка данных на сервер для добавления поста
    fetch('/addPost', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
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