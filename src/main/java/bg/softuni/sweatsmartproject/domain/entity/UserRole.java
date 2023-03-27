package bg.softuni.sweatsmartproject.domain.entity;

import bg.softuni.sweatsmartproject.domain.enums.RoleEnum;
import jakarta.persistence.*;
@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity{
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public UserRole setRole(RoleEnum role) {
        this.role = role;
        return this;
    }

    public RoleEnum getRole() {
        return role;
    }
}