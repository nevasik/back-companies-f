package ru.poplaukhin.AdvertisingCompanies.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "Compain")
@ToString
@Getter
@Setter
public class Compaign {
    @Id
    @GeneratedValue
    @Column(name = "compain_id")
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "budget")
    private Double budget;

    @Column(name = "target_audience")
    private String targetAudience;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "avatar", length = Integer.MAX_VALUE)
    private String avatar;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "statistics_id", referencedColumnName = "statistics_id")
    private Statistics statistics;

    public Compaign() {
    }

//    public Compaign(Integer id, String name, Double budget, String targetAudience, Date startDate, Date endDate, Person person, Statistics statistics) {
//        this.id = id;
//        this.name = name;
//        this.budget = budget;
//        this.targetAudience = targetAudience;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.person = person;
//        this.statistics = statistics;
//    }
}