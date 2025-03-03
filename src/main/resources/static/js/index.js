document.getElementById('addPostBtn').addEventListener('click', function() {
    document.getElementById('addPostForm').style.display = 'block';
});

document.getElementById('postForm').addEventListener('submit', function(e) {
    e.preventDefault();
    const caption = document.getElementById('postCaption').value;
    const image = document.getElementById('postImage').files[0];
    const text = document.getElementById('postText').value;
    const tags = document.getElementById('postTags').value.split(',');

    // AJAX-запрос на добавление поста
    fetch('/post/addPost', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
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

document.getElementById('filterByTag').addEventListener('change', function() {
    const tag = this.value;
    // AJAX-запрос на фильтрацию постов по тегу
    fetch('/filterPosts?tag=' + tag)
        .then(response => response.json())
        .then(data => {
            const postsContainer = document.getElementById('postsContainer');
            postsContainer.innerHTML = '';
            data.forEach(post => {
                const postPreview = document.createElement('div');
                postPreview.classList.add('postPreview');
                postPreview.innerHTML = `
                <a href="/post/${post.id}">${post.caption}</a>
                <img src="${post.imageURL}" alt="Картинка поста">
                <p>${post.text}</p>
                <p>Комментариев: ${post.comments.length}</p>
                <p>Лайков: ${post.likesCount}</p>
                <p>Теги: ${post.tags.map(tag => tag.name).join(', ')}</p>
            `;
                postsContainer.appendChild(postPreview);
            });
        })
        .catch(error => console.error(error));
});

document.getElementById('postsPerPage').addEventListener('change', function() {
    const limit = this.value;
    // AJAX-запрос на пагинацию постов
    fetch('/paginatePosts?limit=' + limit)
        .then(response => response.json())
        .then(data => {
            const postsContainer = document.getElementById('postsContainer');
            postsContainer.innerHTML = '';
            data.forEach(post => {
                const postPreview = document.createElement('div');
                postPreview.classList.add('postPreview');
                postPreview.innerHTML = `
                <a href="/post/${post.id}">${post.caption}</a>
                <img src="${post.imageURL}" alt="Картинка поста">
                <p>${post.text}</p>
                <p>Комментариев: ${post.comments.length}</p>
                <p>Лайков: ${post.likesCount}</p>
                <p>Теги: ${post.tags.map(tag => tag.name).join(', ')}</p>
            `;
                postsContainer.appendChild(postPreview);
            });
        })
        .catch(error => console.error(error));
});
