package by.strunin.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import by.strunin.myblog.model.Post;
import by.strunin.myblog.service.PostService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}") // GET запрос /posts
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

    @PostMapping(path = "/addPost")
    public String addPost(@RequestParam("caption") String caption,
                          @RequestParam("text") String text,
                          @RequestParam("file") MultipartFile file,
                          @RequestParam("tagIds") List<Long> tagIds) {
        String fileName = !file.isEmpty() ? "/uploads/" + file.getOriginalFilename() : null;
        Post post = new Post();
        post.setCaption(caption);
        post.setText(text);
        post.setFileName(fileName);
        post.setLikesCount(0);
        post.setCreationDate(LocalDate.now());
        Long postId = service.save(post);

        service.savePostTags(postId, tagIds);

        if (!file.isEmpty()) {
            try {
                // Указываем путь к папке static/uploads
                // Указываем путь к папке static/uploads
                Path uploadDir = Paths.get("src/main/resources/static/uploads/");

                // Создаем директорию, если она не существует
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }

                // Сохраняем файл
                Path path = uploadDir.resolve(file.getOriginalFilename());
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/";
        //return "redirect:/post";
    }

    @PostMapping(value = "/{id}", params = "_method=delete")
    public String delete(@PathVariable(name = "id") Long id) {
        service.deleteById(id);
        return "redirect:/post";
    }

}
