package com.springapp.familyapp.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FamilyMemberDto {
    Long id;
    Long familyId;
    private String givenName;
    private String familyName;
    private int age;
}
