package ru.poplaukhin.AdvertisingCompanies.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CompaignDto {
    private Integer id;

    @NotEmpty(message = "Название компании не может быть пустой")
    @Size(min = 1, max = 150, message = "Название компании не может быть меньше 2 букв и не больше 100 букв")
    private String name;

    private Double budget;

    @NotEmpty(message = "Целевая аудитория не может быть пустой")
    @Size(min = 2, max = 100, message = "Целевая аудитория не может быть меньше 2 букв и не больше 100 букв")
    private String targetAudience;

    private Date startDate;

    private Date endDate;

    private Integer person; // основатель компании

    private String avatar;

    private Integer statistics; // статистика компании(One to One)
}
