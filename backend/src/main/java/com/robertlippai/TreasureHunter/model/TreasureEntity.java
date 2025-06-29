package com.robertlippai.TreasureHunter.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Treasures")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class TreasureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Boolean wasFound;
    private Long unlockID; // The code hidden in the GeoBox to mark it as found.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    //@JsonIgnore
    private LocationEntity location;
}
