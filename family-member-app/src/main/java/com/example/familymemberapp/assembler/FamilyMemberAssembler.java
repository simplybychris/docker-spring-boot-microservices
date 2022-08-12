package com.example.familymemberapp.assembler;

import com.example.familymemberapp.model.FamilyMember;
import com.example.familymemberapp.model.dto.FamilyMemberDto;

public class FamilyMemberAssembler {
    public static FamilyMemberDto map(FamilyMember family) {
        return FamilyMemberDto.builder()
                .id(family.getId())
                .familyId(family.getFamilyId())
                .familyName(family.getFamilyName())
                .givenName(family.getGivenName())
                .age(family.getAge())
                .build();
    }

    public static FamilyMember create(FamilyMemberDto dto) {
        return update(new FamilyMember(), dto);
    }

    public static FamilyMember update(FamilyMember entity, FamilyMemberDto dto) {
        entity.setFamilyId(dto.getFamilyId());
        entity.setFamilyName(dto.getFamilyName());
        entity.setGivenName(dto.getGivenName());
        entity.setAge(dto.getAge());
        return entity;
    }
}
