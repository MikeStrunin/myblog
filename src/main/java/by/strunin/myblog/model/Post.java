package by.strunin.myblog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Long id;
    private String caption;
    //private String imageURL; // TODO:
    private String text;
    private Integer likesCount;
    //private List<Tag> tags; TODO:
    //private List <Comment> comments; TODO:
    private Date creationDate;
}