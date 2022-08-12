package com.springapp.familyapp.controller;

import com.springapp.familyapp.dao.FamilyRepository;
import com.springapp.familyapp.model.Family;
import com.springapp.familyapp.model.dto.FamilyDto;
import com.springapp.familyapp.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    @PostMapping("/family")
    public Long createFamily(@RequestBody FamilyDto familyDto) {
        return familyService.create(familyDto);
    }

    @GetMapping("/family/{id}")
    public FamilyDto getFamily(@PathVariable Long id) {
        return familyService.getFamily(id);
    }
}
