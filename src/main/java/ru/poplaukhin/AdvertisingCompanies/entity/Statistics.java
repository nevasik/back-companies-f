package ru.poplaukhin.AdvertisingCompanies.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Statistics")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statistics_id")
    private int id;

    @Column(name = "views")
    private int views;

    @Column(name = "clicks")
    private int clicks;

    @Column(name = "conversions")
    private int conversions;

    @Column(name = "date")
    private Date date;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "compain_id", referencedColumnName = "compain_id")
//    private Compaign compaign;

    @Column(name = "performance")
    private BigDecimal performance;

    @Column(name = "is_big")
    private Boolean isBig;
}
