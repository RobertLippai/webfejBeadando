package com.robertlippai.TreasureHunter.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Locations")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String coordinates;

    // Cascade All: bármilyen változtatás ami érinti a location-t a Treasure is végre lesz hajtva (törlés is!)
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TreasureEntity> treasureMaps = new ArrayList<>();
}
