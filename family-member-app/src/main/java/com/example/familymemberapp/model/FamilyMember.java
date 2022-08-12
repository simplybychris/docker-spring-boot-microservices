package com.example.familymemberapp.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "family_member")
public class FamilyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "family_id")
    private Long familyId;

    @Column(name = "family_name")
    private String familyName;

    @NotNull
    @Column(name = "given_name")
    private String givenName;

    @NotNull
    @Column(name= "age")
    private int age;
}
