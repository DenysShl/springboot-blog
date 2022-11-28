package com.example.blog.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userName"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private boolean status;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (status != user.status) {
            return false;
        }
        if (!Objects.equals(id, user.id)) {
            return false;
        }
        if (!Objects.equals(firstName, user.firstName)) {
            return false;
        }
        if (!Objects.equals(lastName, user.lastName)) {
            return false;
        }
        if (!Objects.equals(userName, user.userName)) {
            return false;
        }
        if (!Objects.equals(email, user.email)) {
            return false;
        }
        if (!Objects.equals(password, user.password)) {
            return false;
        }
        if (!Objects.equals(createDate, user.createDate)) {
            return false;
        }
        if (!Objects.equals(updateDate, user.updateDate)) {
            return false;
        }
        return Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", userName='" + userName + '\''
                + ", email='" + email + '\''
                + ", password='" + password + '\''
                + ", status=" + status
                + ", createDate=" + createDate
                + ", updateDate=" + updateDate
                + ", roles=" + roles
                + '}';
    }
}
