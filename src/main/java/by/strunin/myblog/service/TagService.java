package by.strunin.myblog.service;

import by.strunin.myblog.model.Post;
import by.strunin.myblog.model.Tag;
import by.strunin.myblog.repository.post.PostRepository;
import by.strunin.myblog.repository.tag.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }
}