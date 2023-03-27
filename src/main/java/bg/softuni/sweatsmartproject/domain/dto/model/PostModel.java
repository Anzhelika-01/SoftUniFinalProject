package bg.softuni.sweatsmartproject.domain.dto.model;

import bg.softuni.sweatsmartproject.domain.entity.User;
import lombok.*;

import java.io.File;
import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostModel {
    private UUID id;

    private CategoryModel category;

    private String text;

//    private File image;

    public LocalDate creationDate;

    public User author;
}
