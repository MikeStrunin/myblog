document.getElementById('editPostBtn').addEventListener('click', function() {
    document.getElementById('editPostForm').style.display = 'block';
});

document.getElementById('editPostFormSubmit').addEventListener('submit', function(e) {
    e.preventDefault();
    const caption = document.getElementById('editPostCaption').value;
    const image = document.getElementById('editPostImage').files[0];
    const text = document.getElementById('editPostText').value;
    const tags = document.getElementById('editPostTags').value.split(',');

    const postId = document.getElementById('postId').getAttribute('data-post-id');

    // AJAX-запрос на редактирование поста
    fetch('/editPost', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: postId,
    caption: caption,
        image: image,
        text: text,
        tags: tags
})
})
.then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error(error));
});

document.getElementById('deletePostBtn').addEventListener('click', function() {
    // AJAX-запрос на удаление поста
    fetch('/deletePost', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: postId
})
})
.then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error(error));
});

document.getElementById('addCommentBtn').addEventListener('click', function() {
    const commentText = prompt('Введите текст комментария');
    if (commentText) {
        // AJAX-запрос на добавление комментария
        fetch('/addComment', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                postId: postId,
        text: commentText
    })
    })
    .then(response => response.json())
            .then(data => console.log(data))
            .catch(error => console.error(error));
    }
});

document.querySelectorAll('.editCommentBtn').forEach(button => {
    button.addEventListener('click', function() {
        const commentText = prompt('Введите новый текст комментария');
        if (commentText) {
            const commentId = button.parentNode.querySelector('p').getAttribute('data-id');
            // AJAX-запрос на редактирование комментария
            fetch('/editComment', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    id: commentId,
                    text: commentText
                })
            })
                .then(response => response.json())
                .then(data => console.log(data))
                .catch(error => console.error(error));
        }
    });
});

document.querySelectorAll('.deleteCommentBtn').forEach(button => {
    button.addEventListener('click', function() {
        const commentId = button.parentNode.querySelector('p').getAttribute('data-id');
        // AJAX-запрос на удаление комментария
        fetch('/deleteComment', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: commentId
            })
        })
            .then(response => response.json())
            .then(data => console.log(data))
            .catch(error => console.error(error));
    });
});

document.getElementById('likePostBtn').addEventListener('click', function() {
    // AJAX-запрос на лайк поста
    fetch('/likePost', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            postId: postId
})
})
.then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error(error));
});
