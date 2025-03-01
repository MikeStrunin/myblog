package by.strunin.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import by.strunin.myblog.model.Post;
import by.strunin.myblog.service.PostService;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping // GET запрос /posts
    public String posts(Model model) {
        List<Post> posts = service.findAll();
        model.addAttribute("posts", posts);
        return "posts"; // Возвращаем название шаблона — posts.html
    }

    @PostMapping
    public String save(@ModelAttribute Post post) {
        service.save(post);
        return "redirect:/posts"; // Возвращаем страницу, чтобы она перезагрузилась
    }

    @PostMapping(value = "/{id}", params = "_method=delete")
    public String delete(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
        return "redirect:/posts";
    }

}
