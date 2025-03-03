package by.strunin.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import by.strunin.myblog.model.Post;
import by.strunin.myblog.service.PostService;

import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping (value = "/{id}") // GET запрос /posts
    public String getPost(Model model, @PathVariable(name = "id") Long id) {
        Post post = service.getById(id);
        model.addAttribute("post", post);
        return "post"; // Возвращаем название шаблона — post.html
    }

    @PostMapping
    public String save(@ModelAttribute Post post) {
        service.save(post);
        return "redirect:/post"; // Возвращаем страницу, чтобы она перезагрузилась
    }

    @PostMapping(value = "/{id}", params = "_method=delete")
    public String delete(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
        return "redirect:/post";
    }

}
