package by.strunin.myblog.repository.tag;

import by.strunin.myblog.model.Post;
import by.strunin.myblog.model.Tag;

import java.util.List;

public interface TagRepository {
    List<Tag> findAll();
}
