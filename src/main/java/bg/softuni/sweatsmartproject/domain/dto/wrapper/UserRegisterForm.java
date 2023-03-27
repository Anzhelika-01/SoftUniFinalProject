package bg.softuni.sweatsmartproject.domain.dto.wrapper;

import bg.softuni.sweatsmartproject.validation.passwordVaidator.PasswordMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@PasswordMatch
public class UserRegisterForm {

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 4, max = 20)
    private String username;

    @NotNull
    @Size(min = 4, max = 20)
    private String password;

    @NotNull
    private String confirmPassword;
}