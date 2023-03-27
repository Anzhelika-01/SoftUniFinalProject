package bg.softuni.sweatsmartproject.service;

import bg.softuni.sweatsmartproject.domain.dto.model.UserModel;
import bg.softuni.sweatsmartproject.domain.dto.wrapper.UserRegisterForm;
import bg.softuni.sweatsmartproject.domain.entity.User;
import bg.softuni.sweatsmartproject.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Consumer;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;

    @Autowired
    public UserService(UserRepo userRepo, ModelMapper modelMapper, UserRoleService userRoleService, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.userRoleService = userRoleService;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    public void registerUser(UserRegisterForm registerForm, Consumer<Authentication> successfulLoginProcessor) {

        final UserModel userModel = this.modelMapper.map(registerForm, UserModel.class);

        userModel.setRole(Set.of(this.userRoleService.findRoleByName("USER")));
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));

        final User user = this.modelMapper.map(userModel, User.class);

        this.modelMapper.map(this.userRepo.saveAndFlush(user), UserModel.class);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(registerForm.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        successfulLoginProcessor.accept(authentication);
    }
}