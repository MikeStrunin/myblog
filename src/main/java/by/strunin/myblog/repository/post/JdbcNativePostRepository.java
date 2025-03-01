package by.strunin.myblog.repository.post;

import by.strunin.myblog.model.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class JdbcNativePostRepository implements PostRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcNativePostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Post> findAll() {
        // Выполняем запрос с помощью JdbcTemplate, Преобразовываем ответ с помощью RowMapper
        return jdbcTemplate.query(
                "select id, caption, text, likesCount, creationDate from posts",
                (rs, rowNum) ->
                {
                    Post post = new Post();
                    post.setId( rs.getLong("id"));
                    post.setCaption(rs.getString("caption"));
                    post.setText( rs.getString(("text")));
                    post.setLikesCount( rs.getInt("likesCount"));
                    //TODO: tags and comments
                    //post.setCreationDate(rs.getDate("creationDate").toLocalDate());
                    return post;
                });
    }

    @Override
    public void save(Post post) {
        // Формируем insert-запрос с параметрами
        jdbcTemplate.update("insert into posts( caption, text, likesCount, creationDate) values(?, ?, ?, ?)",
                post.getCaption(), post.getText(), post.getLikesCount(), new Date());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("delete from posts where id = ?", id);
    }

}