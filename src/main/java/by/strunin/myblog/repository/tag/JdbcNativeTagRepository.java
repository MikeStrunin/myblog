package by.strunin.myblog.repository.tag;

import by.strunin.myblog.mapper.TagRowMapper;
import by.strunin.myblog.model.Post;
import by.strunin.myblog.model.Tag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class JdbcNativeTagRepository implements TagRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcNativeTagRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Tag> findAll() {
        String sql = "SELECT * FROM tags";
        return jdbcTemplate.query(sql, new TagRowMapper());
    }
}
