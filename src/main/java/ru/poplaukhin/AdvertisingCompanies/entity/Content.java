package ru.poplaukhin.AdvertisingCompanies.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Content")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Content {
    @Id
    @Column(name = "content_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @Column(name = "content_url", nullable = false)
    private String contentUrl;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "compain_id")
    private Compaign compaign;
}
