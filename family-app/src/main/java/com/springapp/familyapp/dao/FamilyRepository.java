package com.springapp.familyapp.dao;

import com.springapp.familyapp.model.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<Family, Long> {
}
