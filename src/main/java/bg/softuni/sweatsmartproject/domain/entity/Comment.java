package bg.softuni.sweatsmartproject.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity{
    @Column
    public LocalDate creationDate;

    @Column
    public String text;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    public User author;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    public Post post;

    public Comment setText(String text) {
        this.text = text;
        return this;
    }
}