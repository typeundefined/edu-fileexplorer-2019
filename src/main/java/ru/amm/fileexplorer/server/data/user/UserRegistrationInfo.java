package ru.amm.fileexplorer.server.data.user;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
@Entity
@Table(name = "users")
public class UserRegistrationInfo {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @Length(max = 255)
    private String username;

    @NotEmpty
    @Length(max = 1024)
    private String fullName;

    @Length(min = 5, max = 255)
    private String password;

    @Length(min = 5, max = 255)
    private String confirmPassword;

    public UserRegistrationInfo() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
