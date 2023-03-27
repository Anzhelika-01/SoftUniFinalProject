package bg.softuni.sweatsmartproject.domain.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private UUID id;

    private String username;

    private String password;

    private String email;

    private Set<UserRoleModel> role;
}
