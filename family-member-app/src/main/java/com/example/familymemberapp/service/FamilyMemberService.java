package com.example.familymemberapp.service;

import com.example.familymemberapp.assembler.FamilyMemberAssembler;
import com.example.familymemberapp.dao.FamilyMemberRepository;
import com.example.familymemberapp.model.dto.FamilyMemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FamilyMemberService {

    @Autowired
    private FamilyMemberRepository familyMemberRepository;

    public void createFamilyMembers(List<FamilyMemberDto> familyMemberDtos) {
        familyMemberRepository.saveAll(
                familyMemberDtos.stream().map(FamilyMemberAssembler::create)
                        .collect(Collectors.toList())
        );
    }

    public List<FamilyMemberDto> getFamilyMembersByFamilyId(Long familyId) {
        return familyMemberRepository.findAllByFamilyId(familyId)
                .stream()
                .map(FamilyMemberAssembler::map)
                .collect(Collectors.toList());
    }
}
