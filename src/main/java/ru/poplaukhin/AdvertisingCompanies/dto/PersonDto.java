package ru.poplaukhin.AdvertisingCompanies.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.poplaukhin.AdvertisingCompanies.entity.Role;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class PersonDto {
    private Integer id;

    @NotEmpty(message = "Фамилия не может быть пустой")
    @Size(min = 2, max = 100, message = "Фамилия не может быть меньше 2 букв и не больше 100 букв")
    private String lastName;

    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 100, message = "Имя не может быть меньше 2 букв и не больше 100 букв")
    private String firstName;

    @Size(min = 5, max = 100, message = "Отчество не может быть меньше 5 букв и не больше 100 букв")
    private String secondName;

    @NotEmpty(message = "Электронный адрес не может быть пустой")
    @Size(min = 9, max = 150, message = "Электронный адрес не может быть меньше 9 букв и не больше 150 букв")
    @Email(message = "Проверьте валидность электронной почты")
    private String email;

    @NotEmpty(message = "Роль не может быть пустой")
    private Role role;

    @NotEmpty(message = "Логин не может быть пустым")
    @Size(min = 3, max = 200, message = "Логин не может быть меньше 3 символов и больше 200 символов")
    private String login;

    @NotEmpty(message = "Пароль не может быть пустым")
    @Size(min = 7, max = 200, message = "Пароль не может быть меньше 7 символов и больше 200 символов")
    private String password;

    private String avatar;
}
