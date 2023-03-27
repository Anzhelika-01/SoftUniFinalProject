package bg.softuni.sweatsmartproject.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.File;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "posts")
public class Post extends BaseEntity{
    @Column(name = "creation_date")
    public LocalDate creationDate;

    @Column
    public String text;

//    @Column
//    public File image;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    public Category category;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    public User author;
}