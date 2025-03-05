package by.strunin.myblog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Long id;
    private String caption;
    private String fileName;
    private String text;
    private Integer likesCount;
    private List<Tag> tags;
    private List <Comment> comments;
    private Integer commentsCount;
    private LocalDate creationDate;
}