package by.strunin.myblog.repository.post;

import by.strunin.myblog.mapper.CommentRowMapper;
import by.strunin.myblog.mapper.PostRowMapper;
import by.strunin.myblog.mapper.TagRowMapper;
import by.strunin.myblog.model.Comment;
import by.strunin.myblog.model.Post;
import by.strunin.myblog.model.Tag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class JdbcNativePostRepository implements PostRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcNativePostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Post> findAll() {
        // Выполняем запрос с помощью JdbcTemplate, Преобразовываем ответ с помощью RowMapper
        List<Post> posts =  jdbcTemplate.query(
                "select * from posts", new PostRowMapper());

        posts.stream().forEach(post -> {
            // Fetch Tags
            String tagsSql = "SELECT t.* FROM tags t JOIN post_tags pt ON t.id = pt.tag_id WHERE pt.post_id = ?";
            List<Tag> tags = jdbcTemplate.query(tagsSql, new Object[]{post.getId()}, new TagRowMapper());
            post.setTags(tags);

            // Fetch Comments
            String commentsSql = "SELECT count(*) FROM comments WHERE post_id = ?";
            Integer commentsCount = jdbcTemplate.queryForObject(commentsSql, Integer.class, new Object[] { post.getId() });
            post.setCommentsCount(commentsCount);
        });
        return posts;
    }

    @Override
    public Long save(Post post) {
        // Формируем insert-запрос с параметрами
        jdbcTemplate.update("insert into posts( id, caption, text, likesCount, creationDate, fileName) values(?, ?, ?, ?, ?, ?)",
                post.getId()!=null? post.getId() : UUID.randomUUID().getLeastSignificantBits() ,post.getCaption(), post.getText(), post.getLikesCount(), post.getCreationDate(), post.getFileName());
        return post.getId();
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("delete from posts where id = ?", id);
    }

    @Override
    public Post getById(Long postId) {
        // Fetch Post
        String postSql = "SELECT * FROM posts WHERE id = ?";
        Post post = jdbcTemplate.queryForObject(postSql, new Object[]{postId}, new PostRowMapper());

        // Fetch Tags
        String tagsSql = "SELECT t.* FROM tags t JOIN post_tags pt ON t.id = pt.tag_id WHERE pt.post_id = ?";
        List<Tag> tags = jdbcTemplate.query(tagsSql, new Object[]{postId}, new TagRowMapper());
        post.setTags(tags);

        // Fetch Comments
        String commentsSql = "SELECT * FROM comments WHERE post_id = ?";
        List<Comment> comments = jdbcTemplate.query(commentsSql, new Object[]{postId}, new CommentRowMapper());
        post.setComments(comments);

        return post;
    }

    public void savePostTags(Long postId, List<Long> tagIds) {
        String sql = "INSERT INTO post_tags (post_id, tag_id) VALUES (?, ?)";
        for (Long tagId : tagIds) {
            jdbcTemplate.update(sql, postId, tagId);
        }
    }

}