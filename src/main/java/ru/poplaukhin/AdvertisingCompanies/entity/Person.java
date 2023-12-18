package ru.poplaukhin.AdvertisingCompanies.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "person")
@ToString
@Getter
@Setter
public class Person {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "last_name", nullable = false, length = 100)
    @NotEmpty(message = "Фамилия не может быть пустой")
    @Size(min = 2, max = 100, message = "Фамилия не может быть меньше 2 букв и не больше 100 букв")
    private String lastName;

    @Column(name = "first_name", nullable = false, length = 100)
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 100, message = "Имя не может быть меньше 2 букв и не больше 100 букв")
    private String firstName;

    @Column(name = "second_name", nullable = false)
    @Size(min = 5, max = 100, message = "Отчество не может быть меньше 5 букв и не больше 100 букв")
    private String secondName;

    @Column(name = "email", nullable = false)
    @NotEmpty(message = "Электронный адрес не может быть пустой")
    @Size(min = 9, max = 150, message = "Электронный адрес не может быть меньше 9 букв и не больше 150 букв")
    @Email(message = "Проверьте валидность электронной почты")
    private String email;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotEmpty(message = "Роль не может быть пустой")
    private Role role; // админ, менеджен, аналитик

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password", unique = true)
    private String password;

    @Column(name = "avatar", length = Integer.MAX_VALUE)
    private String avatar;

    public Person() {
    }

    public Person(String login, String password, String lastName, String firstName, String secondName, String email, Role role) {
        this.login = login;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.role = role;
    }
}
