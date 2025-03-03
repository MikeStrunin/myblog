-- Таблица с пользователями
--create table if not exists posts(
--                                    id bigserial primary key,
--                                    caption varchar(256) not null,
--    text varchar(256) not null,
--    likesCount integer not null,
--    creationDate timestamp null);

CREATE TABLE posts (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       caption VARCHAR(255),
                       text TEXT,
                       likesCount INTEGER,
                       creationDate DATE
);

CREATE TABLE comments (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          text TEXT,
                          creationDate DATE,
                          post_id BIGINT,
                          FOREIGN KEY (post_id) REFERENCES posts(id)
);

CREATE TABLE tags (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(255)
);

CREATE TABLE post_tags (
                           post_id BIGINT,
                           tag_id BIGINT,
                           PRIMARY KEY (post_id, tag_id),
                           FOREIGN KEY (post_id) REFERENCES posts(id),
                           FOREIGN KEY (tag_id) REFERENCES tags(id)
);

-- Insert into posts table
INSERT INTO posts (id, caption, text, likesCount, creationDate)
VALUES
    (1, 'First Post', 'This is the first post.', 10, '2023-01-01'),
    (2, 'Second Post', 'This is the second post.', 20, '2023-01-02'),
    (3, 'Third Post', 'This is the third post.', 15, '2023-01-03'),
    (4, 'Fourth Post', 'This is the fourth post.', 25, '2023-01-04'),
    (5, 'Fifth Post', 'This is the fifth post.', 30, '2023-01-05');

-- Insert into comments table
INSERT INTO comments (id, text, creationDate, post_id)
VALUES
    (1, 'Great post!', '2023-01-01', 1),
    (2, 'Nice job!', '2023-01-02', 2),
    (3, 'Good work!', '2023-01-03', 3),
    (4, 'Excellent!', '2023-01-04', 4),
    (5, 'Fantastic!', '2023-01-05', 5),
    (6, 'Another comment on the first post.', '2023-01-06', 1),
    (7, 'Yet another comment on the second post.', '2023-01-07', 2);

-- Insert into tags table
INSERT INTO tags (id, name)
VALUES
    (1, 'Technology'),
    (2, 'Science'),
    (3, 'Art'),
    (4, 'Music'),
    (5, 'Sports');

-- Insert into post_tags table
INSERT INTO post_tags (post_id, tag_id)
VALUES
    (1, 1), -- Post 1 is tagged with Technology
    (1, 2), -- Post 1 is also tagged with Science
    (2, 3), -- Post 2 is tagged with Art
    (3, 4), -- Post 3 is tagged with Music
    (4, 5), -- Post 4 is tagged with Sports
    (5, 1), -- Post 5 is tagged with Technology
    (5, 3); -- Post 5 is also tagged with Art
