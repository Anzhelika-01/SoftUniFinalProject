package bg.softuni.sweatsmartproject.service;

import bg.softuni.sweatsmartproject.domain.dto.model.UserRoleModel;
import bg.softuni.sweatsmartproject.domain.dto.view.UserRoleViewDto;
import bg.softuni.sweatsmartproject.domain.entity.UserRole;
import bg.softuni.sweatsmartproject.domain.enums.RoleEnum;
import bg.softuni.sweatsmartproject.repository.UserRoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserRoleService {
    private final UserRoleRepo userRoleRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRoleService(UserRoleRepo userRoleRepo, ModelMapper modelMapper) {
        this.userRoleRepo = userRoleRepo;
        this.modelMapper = modelMapper;
    }

    public void dbInit() {
        if (!isDbInit()) {
            List<UserRole> roles = new ArrayList<>();

            roles.add(new UserRole().setRole(RoleEnum.USER));
            roles.add(new UserRole().setRole(RoleEnum.ADMIN));

            this.userRoleRepo.saveAllAndFlush(roles);
        }
    }

    public boolean isDbInit() {
        return userRoleRepo.count() > 0;
    }

    public List<UserRoleViewDto> getAll() {
        return this.userRoleRepo.findAll()
                .stream()
                .map(r -> this.modelMapper.map(r, UserRoleViewDto.class))
                .collect(Collectors.toList());
    }

    public UserRoleModel findRoleByName(String name) {
        return this.modelMapper.map(this.userRoleRepo.findByRole(RoleEnum.valueOf(name))
                        .orElseThrow(NoSuchElementException::new),
                UserRoleModel.class);
    }
}