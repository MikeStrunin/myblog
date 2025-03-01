package by.strunin.myblog.service;

import org.springframework.stereotype.Service;
import by.strunin.myblog.model.Post;
import by.strunin.myblog.repository.post.PostRepository;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public void save(Post post) {
        postRepository.save(post);
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
}