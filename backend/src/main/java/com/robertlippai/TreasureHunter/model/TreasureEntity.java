package com.robertlippai.TreasureHunter.model;

import jakarta.persistence.*;
import lombok.*;

@Entity // megmondja hogy ez egy entitáns és az adatbázisban van a helye
@Table(name = "Treasures")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class TreasureEntity {
    // az ID-et automatikusan fogja generálja mentéskor
    @Id // elsődleges kulcs
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Boolean wasFound;
    private Long unlockID; // elvan rejtve a kis geoboxban a kód amivel fel lehet oldani.

    //Lazy: Betölti a kapcsolatot, de az egész objektumot nem tölti be vele, hogy spóroljon
    //Eager: automatikusan betölti a kapcsolatot, de nem akarjuk ezt?
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    //@JsonIgnore
    private LocationEntity location;
}
