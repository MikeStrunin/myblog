package by.strunin.myblog.repository.post;

import by.strunin.myblog.model.Post;
import java.util.List;

public interface PostRepository {
    List<Post> findAll();

    void save(Post post);

    void deleteById(Long id);

    Post getById(Long id);
}
