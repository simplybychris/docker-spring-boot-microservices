package com.example.familymemberapp.controller;

import com.example.familymemberapp.dao.FamilyMemberRepository;
import com.example.familymemberapp.model.dto.FamilyMemberDto;
import com.example.familymemberapp.service.FamilyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FamilyMemberController {
    @Autowired
    private FamilyMemberService familyMemberService;

    @GetMapping("/family-members/{familyId}")
    public List<FamilyMemberDto> getFamilyMembers(@PathVariable Long familyId) {
        return familyMemberService.getFamilyMembersByFamilyId(familyId);
    }

    @PostMapping("/family-members")
    public void createFamilyMembers(@RequestBody FamilyMemberDto[] familyMemberDtos) {
        familyMemberService.createFamilyMembers(Arrays.stream(familyMemberDtos).toList());
    }
}
