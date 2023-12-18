package ru.poplaukhin.AdvertisingCompanies.dto;


import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class StatisticsDto {
    private int id;

    private int views;

    private int clicks;

    private int conversions;

    private Date date;

    private Integer compainId;

    private BigDecimal performance;

    private Boolean isBig;
}
