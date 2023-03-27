package bg.softuni.sweatsmartproject.service;

import bg.softuni.sweatsmartproject.domain.dto.model.PostModel;
import bg.softuni.sweatsmartproject.domain.dto.wrapper.PostForm;
import bg.softuni.sweatsmartproject.domain.entity.Post;
import bg.softuni.sweatsmartproject.domain.entity.User;
import bg.softuni.sweatsmartproject.repository.PostRepo;
import bg.softuni.sweatsmartproject.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PostService {

    private final PostRepo postRepo;

    private final ModelMapper modelMapper;

    private final UserRepo userRepo;

    @Autowired
    public PostService(PostRepo postRepo, ModelMapper modelMapper, UserRepo userRepo) {
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
    }

    public void makePost(PostForm postForm){
        final PostModel postModel = this.modelMapper.map(postForm, PostModel.class);

//        final User user = this.userRepo.getUserByEmail(loggedUser.getEmail())
//                .orElseThrow();
//
//        postModel.setAuthor(user);
//        postModel.setCreationDate(LocalDate.now());

        final Post post = this.modelMapper.map(postModel, Post.class);

        this.modelMapper.map(this.postRepo.saveAndFlush(post), PostModel.class);
    }
}