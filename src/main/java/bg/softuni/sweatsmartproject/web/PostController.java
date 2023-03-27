package bg.softuni.sweatsmartproject.web;

import bg.softuni.sweatsmartproject.domain.dto.wrapper.PostForm;
import bg.softuni.sweatsmartproject.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;

@Controller
@RequestMapping
public class PostController extends BaseController{

    private final PostService postService;
    public static final String BINDING_RESULT_PATH = "org.springframework.validation.BindingResult.";

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post")
    public ModelAndView getPostInBlog(){
        return super.view("post");
    }

    @GetMapping("/make-post")
    public ModelAndView getMakePostPage(){
        return super.view("make-post");
    }

    @PostMapping("/make-post")
    public ModelAndView postMakingPost(@Valid @ModelAttribute(name = "postForm") PostForm postForm,
                                       BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("postForm", postForm)
                    .addFlashAttribute(BINDING_RESULT_PATH + "postForm", bindingResult);

            return super.view("make-post");
        }

        this.postService.makePost(postForm);
        return super.view("index");
    }

    @ModelAttribute(name = "postForm")
    public PostForm getPostForm(){
        return new PostForm();
    }
}