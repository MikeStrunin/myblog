package by.strunin.myblog.controller;

import by.strunin.myblog.model.Post;
import by.strunin.myblog.model.Tag;
import by.strunin.myblog.service.PostService;
import by.strunin.myblog.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller // Указываем Spring, что этот компонент является контроллером
public class HomeController {

    private final PostService postService;
    private final TagService tagService;

    public HomeController(PostService postService, TagService tagService) {
        this.postService = postService;
        this.tagService = tagService;
    }

    @GetMapping("/")

    public String index(Model model) {
        System.out.println("PROJECT STARTED - INDEX!!!");

        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);

        List<Tag> tags = tagService.findAll();
        model.addAttribute("tags", tags);
        return "index"; // Возвращаем название шаблона — index.html
    }

}
