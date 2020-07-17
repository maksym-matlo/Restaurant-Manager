package com.maximalus.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

//todo create foreign key

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@Entity
@Table(name="USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="FIRST_NAME")
    @NotBlank(message = "Ім'я є обов'язковим")
    private String firstName;

    @Column(name="LAST_NAME")
    @NotBlank(message = "Прізвище є обов'язковим")
    private String lastName;

    @Column(name="EMAIL")
    @NotBlank(message = "Електронна адреса є обов'язковою")
    private String email;

    @Column(name="PASSWORD")
    @NotBlank(message = "Пароль є обов'язковим")
    private String password;

    @Column(name="PASSWORD_C0NFIRM")
    @NotBlank(message = "Повторний пароль є обов'язковим")
    private String passwordConfirm;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public User(User user) {
        this.id = user.id;
        this.email = user.email;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.password = user.password;
        this.passwordConfirm = user.passwordConfirm;
        this.userRole = user.userRole;
    }

    public User(String firstName, String lastName, String email, String password, String passwordConfirm, UserRole userRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.userRole = userRole;
    }
}
