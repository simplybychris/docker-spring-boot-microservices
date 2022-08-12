package com.springapp.familyapp.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "family")
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "family_name")
    private String familyName;

    @Column(name = "infants_no")
    private int nrOfInfants;

    @Column(name = "children_no")
    private int nrOfChildren;

    @Column(name = "adults_no")
    private int nrOfAdults;
}
