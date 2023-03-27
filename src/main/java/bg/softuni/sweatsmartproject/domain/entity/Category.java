package bg.softuni.sweatsmartproject.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category extends BaseEntity{
    @Column
    public String name;

    public Category setName(String name) {
        this.name = name;
        return this;
    }
}