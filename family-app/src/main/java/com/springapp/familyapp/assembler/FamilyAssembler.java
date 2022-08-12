package com.springapp.familyapp.assembler;

import com.springapp.familyapp.model.Family;
import com.springapp.familyapp.model.dto.FamilyDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FamilyAssembler {
    public FamilyDto map(Family family) {
        return FamilyDto.builder()
                .id(family.getId())
                .familyName(family.getFamilyName())
                .nrOfAdults(family.getNrOfAdults())
                .nrOfChildren(family.getNrOfChildren())
                .nrOfInfants(family.getNrOfInfants())
                .build();
    }

    public Family create(FamilyDto dto) {
        return update(new Family(), dto);
    }

    public Family update(Family entity, FamilyDto dto) {
        entity.setFamilyName(dto.getFamilyName());
        entity.setNrOfAdults(dto.getNrOfAdults());
        entity.setNrOfChildren(dto.getNrOfChildren());
        entity.setNrOfInfants(dto.getNrOfInfants());

        return entity;
    }
}
