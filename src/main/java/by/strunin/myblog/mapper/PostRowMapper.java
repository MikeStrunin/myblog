package by.strunin.myblog.mapper;

import by.strunin.myblog.model.Post;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
public class PostRowMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        Post post = new Post();
        post.setId(rs.getLong("id"));
        post.setCaption(rs.getString("caption"));
        post.setText(rs.getString("text"));
        post.setLikesCount(rs.getInt("likesCount"));
        post.setCreationDate(rs.getObject("creationDate", LocalDate.class));
        return post;
    }
}
