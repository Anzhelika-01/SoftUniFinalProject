package bg.softuni.sweatsmartproject.domain.dto.wrapper;

import bg.softuni.sweatsmartproject.domain.dto.model.CategoryModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.File;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostForm {

    private UUID id;

    @NotNull
    private CategoryModel category;

    @NotNull
    @Size(max = 1000)
    private String text;

//    private File image;
}